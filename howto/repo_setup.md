# Konfiguracja GitHub

Tworzysz katalog na projekt. Nastepnie:

> git init
>
>git config --global user.name "Your Name Here"
>
>git config --global user.email "your_email@youremail.com"
>
> git remote add origin git@github.com:OstroS/chooni.git
>

# Utworzenie klucza ssh

> cd
>
> cd ./ssh
>
> ssh-keygen -t rsa -C "your_email@youremail.com"

Wklejasz w konfiguracji githuba zawartosc id_rsa.pub i weryfikujesz:

> ssh -T git@github.com

# Tworzenie brancha
>git branch $branchname
>
>git checkout $branchname // przelacza na nowego brancha

Aby wyslac do zdalnego repo brancha nowego nalezy:
>git config push.default [nothing | matching | tracking | current]
