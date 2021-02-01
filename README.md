# IBAN tikrinimo įrankis
* Įrankis skirtas tikrinti tik lietuviškus IBAN;
* Duomenys apie bankus paimti iš [LB svetainės](https://www.lb.lt/en/iban-and-financial-institution-codes);
* CSV failo duomenys išsaugoti PosgreSQL duomenų bazėje;
* Sistema veikia su bet kurio banko padalinio Lietuvoje IBAN;
* Back-end sukurtas naudojantis Spring Boot karkasu;
* Front-end sukurtas naudojantis Angular karkasu;
* Svetainė įkelta į AWS debesį, nuoroda: [spausti čia](http://totaurestapi-env.eba-dfyvcstc.eu-central-1.elasticbeanstalk.com/).

## Naudojimo instrukcija
* Vieno IBAN tikrinimas vyksta įvedant IBAN;
* Sąrašo tikrinimas vyskta nurodant kiekį, tada įvedant norimus IBAN;
* Sistema nurodo kurie IBAN teisingi, įmanomi;
* Norint sužinoti apie IBAN, paspaudžiama nuoroda lentelėje;
* Paspaudus sukuriama nauja lentelė su duomenimis apačioje;
* Lentelės antras duomo nurodo ar sąskaita priklauso SEB bankui;

## Kitų lietuvos bankų sąskaitų numeriai (patikrinti)
```bash
LT24 3250 0414 2330 7601 - Revolut
LT37 7300 0101 6590 3243 - Swedbank
LT74 7180 0001 7873 3384 - Šiaulių bankas
```