# PA165: Music Library

Let us introduce you the Music Library used for managing songs. Each song has a title, bitrate, its position on the album's playlist, and commentary. For the sake of simplicity, each song belongs to exactly one musician, it is part of exactly one album, and can be of exactly one genre. Each album has basic attributes such as date of release, title, commentary, musician and album art. In order to support compilation albums each album can contain songs of different genres from different musicians.

## Installation

You can install the application by this command:

```
mvn clean install
```

## Running

You can run the web application and REST API by this command:

```
cd music-library-web && mvn tomcat7:run 
```

## Sample user credentials

Email: admin@example.com

Password: password

## REST API documentation

No authentication is required for `GET` requests. HTTP Basic authorization is required for data modification.

- [Albums](docs/rest-api/albums.md)
- [Musicians](docs/rest-api/musicians.md)
- [Genres](docs/rest-api/genres.md)
- [Songs](docs/rest-api/songs.md)
