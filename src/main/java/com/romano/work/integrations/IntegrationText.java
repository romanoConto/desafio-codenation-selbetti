package com.romano.work.integrations;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class IntegrationText
{

	public JSONObject getJSON(String urlString) throws IOException
	{
		JSONObject responseJson = null;

		URL url = new URL(urlString);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(15000);

		conn.connect();

		System.out.println(conn.getResponseCode());
		if (conn.getResponseCode() != 200)
		{
			System.out.println(conn.getResponseCode());
			throw new RuntimeException("" + conn.getResponseCode());
		}

		InputStream is = conn.getInputStream();
		if (is != null)
		{

			Writer writer = new StringWriter();
			char[] buffer = new char[1024];

			try
			{
				Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1)
				{
					writer.write(buffer, 0, n);
				}
			}
			finally
			{
				is.close();
			}

			responseJson = new JSONObject(writer.toString());

			System.out.println("Restorno request texto: " + responseJson.toString());
		}
		return responseJson;
	}

	public boolean sendJSONFile(String urlString, File file) throws IOException
	{
		try
		{

			FileBody fileBody = new FileBody(file);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addPart("answer", fileBody);
			HttpEntity entity = builder.build();

			HttpPost request = new HttpPost(urlString);
			request.setEntity(entity);

			CloseableHttpClient client = HttpClientBuilder.create().build();

			client.execute(request);

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
