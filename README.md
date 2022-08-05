# QLM Reklám kezelő alkalmazás

## Megvalósítás

A feladat egy Spring boot projekt keretében került megvalósításra JAVA 8 környezetben.
A napon belüli megjelnítések meghatározása súlyozott valószínűségen alapszik, figyelembe véve a napi maximum megjelenítés értékét.

## Tesztelés

Az alkalmazás teszteléséhez a **Swagger UI** felület használható.
Ez a feladat a http://localhost:8080/swagger-ui/ linken érhető el.
3 API került létrehozásra:
  - **/advertisement** - Használatával egy új reklámot lehet regisztrálni
  - **/advertisements** - Használatával le lehet kérni a már beregisztrált reklámokat a megjelenítési információval kiegészítve.
  - **/showNextAdvertisement** - Adott napra megjelenít egy reklámot

## Futtatás

Clone the project

```bash
  git clone https://github.com/laszlo-juhasz/qlm-advertisement.git
```

Go to the project directory

```bash
  cd qlm-advertisement
```

Install dependencies

```bash
  mvn install
```

Go to the target directory

```bash
  cd target 
```

Start the server

```bash
  java -jar advertisement-0.0.1-SNAPSHOT.jar
```

