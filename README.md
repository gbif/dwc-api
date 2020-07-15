# Darwin Core API

The DwC-API library provides:
 * Enumerations representing [Darwin Core](http://rs.tdwg.org/dwc/) terms
 * Enumerations representing terms or a subset of terms from other standards (e.g. [Dublin Core](http://dublincore.org/documents/dcmi-terms/), [Audubon Core](https://tdwg.github.io/ac/))
 * Handling of unknown terms through [UnknownTerm](https://gbif.github.io/dwc-api/apidocs/org/gbif/dwc/terms/UnknownTerm.html)
 * Factory for getting Term instances
 * Utilities like JSON serialization and Term comparator

## To build the project
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

## Documentation
[JavaDocs](https://gbif.github.io/dwc-api/apidocs/)
