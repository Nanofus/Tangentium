# Ohjelman rakenteen kuvaus

Pääohjelma käyttää useaa Reader-loppuista luokkaa ladatakseen tiedostoista pelin tasot. Tämän jälkeen main käynnistää käyttöliittymän
sekä peli-instanssin Game. Peli puolestaan lataa saamastaan tasolistasta kunkin Level-tyyppisen tason vuorollaan.

Taso koostuu seuraavista komponenteista:
* Taulukko Tile-tyypin ruutuja
* Pelaaja-Actor
* EntityManager, joka sisältää listat vihollisista (Actor) ja esineistä (Item)
* CombatHandler, joka hoitaa actorien väliset taistelut

EntityManager sisältää listat vihollisina toimivista Actoreista sekä esineistä. Esineet ja Actorit molemmat perivät luokan Entity, joka kuvaa pelimaailmassa olevaa hahmoa tai esinettä ja sisältää lähinnä nimen ja sijaintitiedon. CombatHandler käyttää EntityManageria poistaessaan vihollisia pelistä.

Jos Actor on tekoälyn kontrollissa, sillä on käytettävissään luokka AI, joka vastaa sen siirroista (eikä InputManagerista Gamen kautta tuleva input, kuten muutoin).

Player ja Enemy ovat tarkoituksellisesti sama luokka, eivätkä luokan Actor perillisiä. Tämä mahdollistaa tulevassa laajentamisessa "mielenhallintakyvyn": pelaaja voi tarvittaessa ohjata vihollista tai vihollinen pelaajaa.

Actor käyttää CombatHandleria taistelun suorittamiseen, sekä pitää hallussaan nollaa tai yhtä Item-esinettä.

Useat luokat käyttävät pelimaailman sijaintitiedon välittämiseen apuluokan Position instansseja, jotka sisältävät samassa oliossa sekä X- että Y-koordinaatin.