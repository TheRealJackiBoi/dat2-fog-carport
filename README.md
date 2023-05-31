# 1. års projekt - Datamatiker

Dette repository er lavet i forbindelse med 2. semester Datamatikeruddannelsen i Lyngby 2023.

## Java version
I pom.xml er diverse dependencies valgt, så projektet kan bygges og køres i Java 8. Nyere versioner af 
Java fungerer også.

## Tomcat
Brug version 9.x

## Deployment på Droplet
http://cudia.dk:8080/fog

## Bemærkninger

### Startkoden indeholder følgende:

- Strukturering i passende packages for overblik (MVC). Noget af strukturen er også givet af Maven, og kan ikke laves om. F.eks. opdelingen i `/java` og `/webapp`.
- Javaservlets
- JSP sider. Læg dem i WEB-INF som kun skal tilgås via en servlet. Der ligger allerede `welcome.jsp`
- En super skrabet css-fil til styling
- Datamapper for user-tabellen, som anvender en connection pool. Den er package-protected
- En facadeklasse `UserFacade`, der bruges til at tilgå dine mappermetoder
- Fejlhåndtering med exceptions for databaseoperationer. Den skriver også til Tomcat log.
- Integrationstest af datamapperen for User.

### Funktionelt kan applikationen:

- Vise en forside med links til undersider, som endnu ikke er lavet
- Logge en user på. Der findes to brugere i databasen.
    1. `user` med password: `1234` (rolle: `user`)
    2. `admin` med password: `1234` (rolle: `admin`)
- Man kan se på `index.jsp` og `WEB-INF/welcome.jsp` hvordan man kan udnytte om en user er logget på eller ej.
- Hvis man indtaster ugyldige data under indlogning, bliver man sendt til en en fejlside.
- Logge en bruger af
- Metoden `isRoleAllowed(String role, HttpServletRequest request)` som ligger i pakken `services`. Den tjekker om en given bruger matcher en given rolle.

## MVC arkitektur

![](documentation/mvc.jpg)
