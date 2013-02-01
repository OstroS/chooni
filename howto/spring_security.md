Kolejność wywołychwanych metod/obiektów/bytów:

1. Użytkownik trafia na pattern zdefiniowany w web.xml określający filtr dla Spring Security.
2. Użytkownik trafia na pattern określony w <http><intercept-url>
3. Użytkownik leci na adres loginUrlAuthenticationEntryPoint.loginFormUrl
4. Tu dzieje się magia po której użytkownik ma trafić na adres zdefiniowany jako filterProcessUrl w filtrze, który został uprzednio dodany do http.custo-filter
5. Tutaj uruchamia się Authentication filter - wyciągane są odpowiednie obiekty z requestu i tworzony jest obiekt użytkownika.
6. Następnie uruchamiany jest authentication provider, którego celem jest wyciągnięcie z bazy danych użytkownika, nadanie mu uprawnień i zwrócenie go do kontekstu.

Tutaj magia się kończy.

Ze względu na potencjalną złożoność obiektu użytkownika (principle) komunikacja między filtrem i authentication providerem odbywa się poprzez obiekt transferowy.
