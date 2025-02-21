# csvw-check

Validate CSV-W based on tests provided by W3C (https://w3c.github.io/csvw/tests/#manifest-validation)

## Using csvw-check

### Help

```bash
$ csvw-check --help
csvw-check 0.0.1
Usage: csvw-check [options]

  -s, --schema <value>     filename of Schema/metadata file
  -c, --csv <value>        filename of CSV file
  -l, --log-level <value>  OFF|ERROR|WARN|INFO|DEBUG|TRACE
  -h, --help               prints this usage text
```

### Docker

```bash
$ docker pull roblinksdata/csvw-check:latest
$ docker run --rm roblinksdata/csvw-check:latest -s https://w3c.github.io/csvw/tests/test011/tree-ops.csv-metadata.json
Valid CSV-W
```

### Not Docker

Acquire the latest universal 'binary' ZIP file from the releases tab (e.g. [csvw-check-0.3.1.zip](https://github.com/roblinksdata/csvw-check/releases/download/v0.3.1/csvw-check-universal.zip)).

```bash
$ bin/csvw-check -s https://w3c.github.io/csvw/tests/test011/tree-ops.csv-metadata.json
Valid CSV-W
```
