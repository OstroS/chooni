# Konfiguracja GitHub

Tworzysz katalog na projekt. Nastepnie:

> git init
>
>git config --global user.name "Your Name Here"
>
>git config --global user.email "your_email@youremail.com"
>
> git remote add origin https://github.com/username/chooni.git 

# Utworzenie klucza ssh

> cd
>
> cd ./ssh
>
> ssh-keygen -t rsa -C "your_email@youremail.com"

Wklejasz w konfiguracji githuba zawartosc id_rsa.pub i weryfikujesz:

> ssh -T git@github.com
