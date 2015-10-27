# dwc-api

A term enumeration and factory for [Darwin Core](http://rs.tdwg.org/dwc/) and similar terms.

## Build
```
mvn clean install
```

## Usage
```java
//get Term by the defined enumaration
Term scientificNameFromEnum = DwcTerm.scientificName;

//get Term by the TermFactory
TermFactory factory = TermFactory.instance();
Term scientificNameFromPrefixAndSimpleName = factory.findTerm("dwc:scientificName");
Term scientificNameFromQualifiedName = factory.findTerm("http://rs.tdwg.org/dwc/terms/scientificName");
```

## Requirements
 * Build as Java 6 artifact until the [IPT](https://github.com/gbif/ipt) moves to Java 7.