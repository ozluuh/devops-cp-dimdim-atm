# API : DevOps CP04 - ATM Dim Dim

Essa seção realiza a documentação da **API**. Para visualizar a versão com interface gráfica (navegador) da aplicação, acesse [aqui](../README).

&#x1F4A1; A API encontra-se disponível no seguinte endereço: `http://localhost:8080/api/<endpoint>`

Atualmente os seguintes endpoints estão disponíveis:

- `/api/customer`: serviços de cliente.

  - `POST /api/customer`: Cria um novo cliente;

    ```jsonc
    // Request Body
    {
      "name": "Alexandre",
      "surname": "Geraldo Lopes",
      "sex": "M",
      "account": {
        "agency": "1111",
        "number": "123654",
        "type": "PREMIUM"
      }
    }

    // Response Body
    {
    "id": 1,
    "name": "Alexandre",
    "surname": "Geraldo Lopes",
    "sex": "M",
    "account": {
        "id": 1,
        "agency": "1111",
        "number": "123654",
        "balance": 0.0,
        "accountLimit": 100.0,
        "type": "DEFAULT"
    }
    }
    ```

  - `GET /api/customer`: obtém todos os clientes cadastrados

    ```jsonc
    // No Request body required
    // Response Body
    [
      {
        "id": 1,
        "name": "Alexandre",
        "surname": "Geraldo Lopes",
        "sex": "M",
        "account": {
          "id": 1,
          "agency": "1111",
          "number": "123654",
          "balance": 0.0,
          "accountLimit": 100.0,
          "type": "DEFAULT"
        }
      }
    ]
    ```

  - `GET /api/customer/{id}`: exibe os dados do cliente

    ```jsonc
    // No Request body required
    // Response Body
    {
      "id": 1,
      "name": "Alexandre",
      "surname": "Geraldo Lopes",
      "sex": "M",
      "account": {
        "id": 1,
        "agency": "1111",
        "number": "123654",
        "balance": 0.0,
        "accountLimit": 100.0,
        "type": "DEFAULT"
      }
    }
    ```

- `/api/customer/{id}/bankServices`: serviços bancários.

  - `GET /api/customer/{id}/bankServices/statement`: exibe as transações do cliente

    ```jsonc
    // No Request body required
    // Response Body
    [
      {
        "id": 1,
        "transactionType": "C",
        "value": 12509.0,
        "balance": 12509.0,
        "history": "TESTE TRANSACAO DEPOSITO",
        "movementDate": "2021-09-07"
      }
    ]
    ```

  - `POST /api/customer/{id}/bankServices/withdraw`: realiza a operação bancária de saque

    ```jsonc
    // Request body
    {
      "value": 8.5,
      "description": "Ebanx*Spotify"
    }
    // No Response body returned
    ```

  - `POST /api/customer/{id}/bankServices/deposit`: realiza a operação bancária de depósito
    ```jsonc
    // Request body
    {
      "value": 8.5,
      "description": "Ebanx*Spotify"
    }
    // No Response body returned
    ```
