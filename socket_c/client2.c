#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>
#include "string_util.h"

int main() {
    int clientSocket;
    char buffer[1024];
    struct sockaddr_in serverAddr;
    socklen_t addr_size;
    int cmdEXIT = 0;

    // Creazione del socket del client
    clientSocket = socket(PF_INET, SOCK_STREAM, 0);

    if (clientSocket == -1) 
    {
        perror("Errore durante la creazione del socket");
        exit(EXIT_FAILURE);
    }

    // Configurazione dell'indirizzo del server
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(7891);
    serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");
    memset(serverAddr.sin_zero, '\0', sizeof serverAddr.sin_zero);
    addr_size = sizeof serverAddr;

    if (connect(clientSocket, (struct sockaddr*)&serverAddr, addr_size) == -1) {
        perror("Errore durante la connessione al server");
        close(clientSocket);  // Close the socket in case of an error
        exit(EXIT_FAILURE);
    }

    while (cmdEXIT == 0)
    {
        // Ricezione dei messaggi dal server
        int recvValue = recv(clientSocket, buffer, sizeof buffer - 1, 0);
        if (recvValue != 1)
        {        
            if (compare_strings(buffer, "exit")==-1)
            {
                // Stampa del messaggio ricevuto dal server
                printf("Client 1 : ");
                printf("%s\n", buffer);
                memset(&buffer[0], 0, sizeof(buffer));

            }
            else cmdEXIT = 1;
        }
        else
        {
            // Input del messaggio da parte dell'utente e invio al server
            printf("Client 2 : ");
            scanf(" %[^\n]s", buffer);
            send(clientSocket,buffer,sizeof buffer - 1,0);
            if (compare_strings(buffer, "exit")==-1)
            {
                memset(&buffer[0], 0, sizeof(buffer));
            }
            else cmdEXIT = 1;
        }   
    }
    return 0;
}