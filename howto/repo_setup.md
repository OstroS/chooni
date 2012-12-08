# Konfiguracja GitHub

1. 

# Utworzenie klucza ssh

> cd
> cd ./ssh
> ssh-keygen -t rsa -C "your_email@youremail.com"

Wklejasz w konfiguracji githuba zawartosc id_rsa.pub i weryfikujesz:

> ssh -T git@github.com
