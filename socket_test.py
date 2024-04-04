import socket
from _thread import *
import sys

input = sys.stdin.readline

HOST = '127.0.0.1'
PORT = 8081

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect((HOST, PORT))

def recv_data(client_socket):
    try:
        while True:
            data = client_socket.recv(1024)
            print("recive >> ", repr(data.decode()))
    except:
        client_socket.close()
        client_socket.send("{\"type\":\"SYSTEM_EXIT\",\"data\":\"\"}")
        exit()

try:
    start_new_thread(recv_data, (client_socket,))
    print('>> Connect to Server ' + HOST + ":" + str(PORT))

    while True:
        message = input()
        client_socket.send(message.encode())

except:
    client_socket.send("{\"type\":\"SYSTEM_EXIT\",\"data\":\"\"}".encode())
    client_socket.close()
    exit()