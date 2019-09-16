package com.romano.work.solveText;

public class TextVO
{
	private int numero_casas;

	private String token;

	private String cifrado;

	private String decifrado;

	private String resumo_criptografico;

	public int getNumCasas()
	{
		return numero_casas;
	}

	public void setNumCasas(int numCasas)
	{
		this.numero_casas = numCasas;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String getCifrado()
	{
		return cifrado;
	}

	public void setCifrado(String cifrado)
	{
		this.cifrado = cifrado;
	}

	public String getDecifrado()
	{
		return decifrado;
	}

	public void setDecifrado(String decifrado)
	{
		this.decifrado = decifrado;
	}

	public String getResumoCriptografico()
	{
		return resumo_criptografico;
	}

	public void setResumoCriptografico(String resumoCriptografico)
	{
		this.resumo_criptografico = resumoCriptografico;
	}
}
