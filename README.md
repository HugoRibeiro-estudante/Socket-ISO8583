Socket-ISO8583

Projeto desenvolvido em Java para estudar a implementação do protocolo ISO 8583 utilizando comunicação via Sockets (TCP/IP). O objetivo principal é entender como funciona o empacotamento, envio e desempacotamento de mensagens de transações financeiras em baixo nível.

🚀 O que o projeto faz
O sistema simula a troca de mensagens entre um cliente (terminal/POS) e um servidor (autorizador), cobrindo:

Abertura de conexão via Sockets.

Montagem do MTI (Message Type Indicator).

Geração e leitura do Bitmap (campos presentes na mensagem).

Tratamento de campos fixos e variáveis.

🛠️ Tecnologias
Java (Lógica de backend e manipulação de bytes).

Sockets TCP/IP (Protocolo de comunicação).

📂 Estrutura Básica
Servidor: Fica aguardando conexões na porta definida para processar as requisições ISO.

Cliente: Monta a string da mensagem ISO 8583 e envia para o servidor.

Parser/Handler: Lógica responsável por identificar cada campo da mensagem de acordo com o padrão.
