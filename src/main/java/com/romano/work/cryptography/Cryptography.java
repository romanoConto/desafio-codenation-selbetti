package com.romano.work.cryptography;

import com.romano.work.solveText.TextVO;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptography
{

	public TextVO decrypts(TextVO text)
	{

		String textoCifrado = text.getCifrado();
		String textoDecifrado = "";
		int numCasas = text.getNumCasas();

		char[] chars = textoCifrado.toCharArray();

		for (int i = 0; i < textoCifrado.length(); i++)
		{
			char c = chars[i];
			if (Character.isAlphabetic(c))
			{
				for (int j = 0; j < numCasas; j++)
				{
					if (numCasas > 0)
					{
						c--;
					}
				}
				chars[i] = c;
			}
		}

		textoDecifrado = String.valueOf(chars);
		System.out.println("Texto decifrado: " + textoCifrado);
		text.setDecifrado(textoDecifrado);

		System.out.println(
			"Resultado da descriptografia: " + validate(textoCifrado, textoDecifrado, numCasas));

		return text;
	}

	private boolean validate(String textoCifrado, String textoDecifrado, int numCasas)
	{
		char[] chars = textoDecifrado.toCharArray();

		for (int i = 0; i < textoDecifrado.length(); i++)
		{
			char c = chars[i];
			if (Character.isAlphabetic(c))
			{
				for (int j = 0; j < numCasas; j++)
				{
					if (numCasas > 0)
					{
						c++;
					}
				}

				chars[i] = c;
			}
		}

		return textoCifrado.equals(String.valueOf(chars));
	}

	public TextVO resum(TextVO text) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		String textoDecifrado = text.getDecifrado();

		MessageDigest algorithm = MessageDigest.getInstance("SHA-1");
		byte messageDigest[] = algorithm.digest(textoDecifrado.getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest)
		{
			hexString.append(String.format("%02X", 0xFF & b));
		}

		String resumoCifrado = hexString.toString();
		resumoCifrado = resumoCifrado.toLowerCase();
		System.out.println("Resumo cifrado: " + resumoCifrado);
		text.setResumoCriptografico(resumoCifrado);

		return text;
	}
}
