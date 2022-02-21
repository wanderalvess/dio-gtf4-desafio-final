# Projeto do desafio final curso GFT Start #4 Java


**Após executar o projeto esse é o link que deve ser acessado via navegador:**
> (http://127.0.0.1:8080/swagger-ui/index.html#/)

**No explorer passar esse valor:** 
> /v3/api-docs

**Request URL:**
> http://127.0.0.1:8080/clientes

**Exemplo de JSON no método POST:**

```
Response body
{
  "name": "Wanderson",
  "address": {
    "cep": "75254794"
  }
}
```

**Exemplo de resultado esperado:**

```
Response body
{
  "id": 1,
  "name": "Wanderson",
  "address": {
    "cep": "75254-794",
    "logradouro": "Rua São Luiz",
    "complemento": "",
    "bairro": "Conjunto Margarida Procópio",
    "localidade": "Senador Canedo",
    "uf": "GO",
    "ibge": "5220454",
    "gia": "",
    "ddd": "62",
    "siafi": "9753"
  }
}
```
