## Projektni zadatak za Metode i tehnike testiranja programske podrške

Ovaj projekt implementira okvir (framework) za automatsko testiranje web aplikacija koristeći Selenium WebDriver i TestNG.
Testovi su napisani u Java programskom jeziku i koriste Page Object Model (POM) kako bi se osigurala modularnost i održivost koda.

Projekt sadrži pet automatiziranih testova koji provjeravaju različite funkcionalnosti web stranica, uključujući pretragu, navigaciju, interakciju s elementima i prijavu korisnika.

## Alati i tehnologije korištene u projektu
- Programski jezik: Java
- Testni okvir: TestNG
- Automatizacijski alat: Selenium WebDriver
- Struktura projekta: Page Object Model (POM)
- Upravljanje ovisnostima: Maven
- IDE za razvoj: IntelliJ IDEA
- Upravljanje izvornim kodom: GitHub

### Page Object Model (POM) korišten je u sljedećim testovima:
`src/main/java/org/example/pages/`
AmazonSearchPage.java – Koristi se u ThirdTest.java za pretragu proizvoda na Amazonu.
SearchPage.java – Koristi se u FourthTest.java (IMDB) i FifthTest.java (Weather.com) za pretragu i rukovanje Cookie popup prozorima.

`src/test/java/org/example/tests/`
ThirdTest.java koristi AmazonSearchPage za interakciju s pretragom na Amazonu.
FourthTest.java i FifthTest.java koriste SearchPage za pretragu na IMDB-u i Weather.com.

### Eksplicitne naredbe za čekanje (WebDriverWait) korištene su u sljedećim testovima:
`src/test/java/org/example/tests/`
SecondTest.java (YouTube test)

Čeka da se pojavi Accept Cookies gumb, da se učita video, koristi JavaScript Executor da provjeri trajanje videa i čeka da se učita prvi rezultat pretrage prije klika.

### Opis testova
FirstTest (Wikipedia pretraga)
- Otvara Wikipedia.org
- Pretražuje "Selenium WebDriver"
- Klikne na prvi rezultat pretrage

### SecondTest (YouTube video test)
- Otvara YouTube
- Čeka (wait) i zatvara Cookie popup (ako postoji)
- Pretražuje "Bohemian Rhapsody"
- Čeka (wait) da se prikaže polje za pretragu prije unosa teksta
- Klikne na prvi video u rezultatima pretrage
- Čeka (wait) da se učita video player (movie_player)
- Čeka (wait) da se prikaže naslov videa
- Koristi wait unutar petlje za provjeru vremena reprodukcije videa do 30 sekundi
- Provjerava je li naslov videa vidljiv

### ThirdTest (Amazon pretraga – POM korišten)
- Otvara Amazon.com
- Pretražuje "Wireless Headphones" koristeći POM (AmazonSearchPage)
- Klikne na prvi rezultat pretrage
- Provjerava je li naslov proizvoda relevantan (sadrži riječ "headphone")

### FourthTest (IMDB pretraga – POM korišten)
- Otvara IMDB.com
- Pretražuje "Inception" koristeći POM (SearchPage)
- Klikne na prvi film iz rezultata pretrage
- Koristi wait za čekanje učitavanja prvog rezultata prije klika

### FifthTest (Weather.com pretraga – POM korišten, koristi Cookie handler)
- Otvara Weather.com
- Automatski zatvara Cookie popup koristeći handleCookies()
- Pretražuje "Zagreb" koristeći POM (SearchPage)
- Klikne na prvi rezultat pretrage
- Koristi wait za čekanje da se prikaže ispravan grad ("Zagreb")
- Provjerava je li prikazan ispravan grad ("Zagreb")
