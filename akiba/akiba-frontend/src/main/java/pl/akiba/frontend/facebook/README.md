# Proof of concept integracji z facebookiem

Aby to zadzaialalo nalezy:

* zdeployowac aplikacje
* ustawic w pliku /etc/hosts nastepujace mapowanie:
> 127.0.0.1	mywebappthatusesfacebook.com
Facebook po zalogowaniu uzytkownika robi przekierowanie na nasza aplikacje. W parameterach wywolania jednego z requestow podaje sie callback location, ale zabezpieczenia facebooka nie umozliwiaja podanie tam adresu _localhost_

* wejsc na adres mywebappthatusesfacebook.com/akiba-frontend/fb/logn
* let the magic begin :)

Dokumentacja do facebooka wyglada nastepujaco:

https://developers.facebook.com/ <= ogolne kwestie
https://developers.facebook.com/docs/howtos/login/server-side-login/ <= opis logowania server-side na przyjkladzie PHP
https://developers.facebook.com/docs/concepts/login/login-architecture/ <= diagramy

W ramach tego PoC nie zostalo zaimplementowane wszystko (np. uprawnienia, obsluga sytuacji wyjatkowych, modul do SpringSecurity), ale juz znamy przynajmniej API i mechanizm dzialania ;)
