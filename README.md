# WebParser
Java parser на основе Jsoup для компактной выгрузки блога со статьями по 
веб-разработки и https://web.dev/blog. Используется markdown, для выгрузки 
на веб-сервер можно Jade или другйо серверный шаблонизатор. 

## Parser

SiteParser.class
- parse(String html): Document

Printer.class
- print(): void
- save(String destination): void

## Connecor
Connector.class
- getPageContent(URLConnection connection): StringBuilder
- getPageStringContent(URLConnection connection): String 
- getDocument(URLConnection connection): Document 
- go(String query): URLConnection
- getWebAddress(): String

## Components 

TextLink.class
- getDescription(): String
- getLink(): String

## Formatters

Formatter.class
- format()

