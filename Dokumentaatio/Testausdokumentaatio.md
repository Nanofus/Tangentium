# Testausdokumentaatio

Pelin suuren koodimäärän ja satunnaisuuden vuoksi Pitin kertoma mutaatiokattavuus jäi reiluun 40 prosenttiin. Esimerkiksi kaikkia pelissä määriteltyjä tile- ja vihollistyyppejä ei käytetä testitasoissa, eikä Pitin rivikattavuustestaus pääse käsiksi jokaiseen vihollisten tekoälyn riviin sen arvaamattomien päätösten vuoksi. Täydensin tätä testausta siis tekemällä korvaavia testejä manuaalisesti.

## JUnit-testit

### fi.nano.tangential.fileProcessing:
* LevelReaderTest - Testaa, että tiedostonlukija lukee tasotiedoston oikein

### fi.nano.tangential.gameLogic
* AITest - Testaa tekoälyn tilojen muutoksia
* EntityTest - Testaa Entityjen liikkumista
* LevelTest - Testaa suurinta osaa Level-luokan metodeista, kuten etäisyyksien mittaamista, vipujen toimintaa, esineiden ja actorien hakua sekä tason parametrien validointia

### fi.nano.tangential.gameLogic.entities
* ActorTest - Testaa Actorin toimintaa; liikkumista, esineiden käyttämistä, toimintojen käyttämistä tyrmättynä, vipujen aktivointia

### fi.nano.tangential.gameLogic.enums
* TileTypeTest - Testaa TileType-enumeraattorin metodia is(), joka tarkastelee TileTypejen hierarkiaa

### fi.nano.tangential.gameLogic.singletons
* CombatHandlerTest - Testaa taistelusysteemin toimintaa, vahingon tekemistä ja vahingon vastustustyyppejä
* EntityManagerTest - Testaa esineiden lisäämistä ja poistamista EntityManagerissa

### Muuta

Kaikilla luokilla ei ole omaa testiluokkaa, sillä niiden toiminta liittyy oleellisesti muiden luokkien toimintaan; Item esimerkiksi on merkittävä luokka, mutta omalta sisällöltään hyvin yksinkertainen. Siksipä kaikki Itemin testaus sisältyy sitä käyttävän Actorin testeihin.

## Manuaalinen testaus

Tekoälyn ja käyttöliittymän toiminta on testattu pitkälti manuaalisesti:

### Tekoäly

Tekoälyn vainoamismekanismi aktivoituu tietyllä etäisyydellä pelaajahahmosta, ja vihollinen lähtee tällöin pelaajan perään liikkuen kuitenkin hieman arvaamattomasti. Tekoäly seikkailee pelikentällä satunnaisesti myös silloin, kun se ei näe pelaajaa. Siksi testasin tekoälyn toimintaa manuaalisesti. Ohjailin pelaajahahmoa vihollisten lähellä ja ympärillä nähdäkseni niiden käyttäytymisen. Tällaisella testauksella sain paikallistettua bugin, joka sai viholliset syöksymään holtittomasti ylöspäin pelaajan nähdessään. Testipelatessa tekoälyn toimivuus lähes kaikissa mahdollisissa tilanteissa varmistui.

### Käyttöliittymä

Käyttöliittymän terveys-sydänten, stun-tähtien ja muiden kuvakkeiden oikea toimivuus on testattu komentoriville tulostetuilla viesteillä, jotka kertovat oikeat arvot.

Isometrisillä grafiikoilla piirtojärjestys tuli testata; tein sen tekemällä tasoja, joihin esineet, viholliset ja eri tile-tyypit oli aseteltu taktisesti niin, että näki niiden toimivan oikein.