package com.romano.work.solveText;

import com.google.gson.Gson;
import com.romano.work.cryptography.Cryptography;
import com.romano.work.integrations.IntegrationText;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

public class SolveText {

    private String token;
    private String getJsonUrl = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=";
    private String sendFileUrl = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=";

    public SolveText(String token) {
        this.token = token;
    }

    public boolean solve() {
        try {
            IntegrationText requestText = new IntegrationText();

            JSONObject jsonText = requestText.getJSON(getJsonUrl + token);

            TextVO textVO = new Gson().fromJson(jsonText.toString(), TextVO.class);
            textVO.setToken(token);

            Cryptography crypt = new Cryptography();
            textVO = crypt.decrypts(textVO);
            textVO = crypt.resum(textVO);

            File file = generateFile(textVO);
            return requestText.sendJSONFile(sendFileUrl + token, file);

        } catch (Exception e) {
            return false;
        }
    }

    private File generateFile(TextVO textVO) throws IOException {

        File file = new File("src\\files\\answer.json");

        Gson gson = new Gson();
        String json = gson.toJson(textVO); //convert
        System.out.println("Conteudo do objeto: " + json);

        FileWriter writeFile = new FileWriter("src\\files\\answer.json");
        //Escreve no arquivo conteudo do Objeto JSON
        writeFile.write(json);
        writeFile.close();

        return file;
    }
}
