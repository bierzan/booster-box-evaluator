package com.brzn.box_eval.card.domain

import com.brzn.box_eval.card.dto.CardDto
import com.fasterxml.jackson.databind.ObjectMapper
import io.vavr.collection.List
import org.apache.commons.io.FileUtils
import org.json.JSONArray
import org.json.JSONObject
import spock.lang.Specification

import java.nio.charset.Charset
import java.time.LocalDate

class CardMapperTest extends Specification { //todo przygotowywanie testowych jsonow w locie?
    private File file
    def fileAsJson = parseReferenceFileToJson()
    private List<CardDto> cards
    def cardMapper = new CardMapper(new ObjectMapper())

    def "should parse json file to cards list"() {
        given:
        setFilePath("src/test/resources/cardBulkData.json")
        when:
        cards = cardMapper.fromJsonListFile(file)
        then:
        cards.map({ c -> c.uuid }).sort() == getPropertiesFromJsonFile("id").sort()
        cards.map({ c -> c.name }).sort() == getPropertiesFromJsonFile("name").sort()
        cards.map({ c -> c.setName }).sort() == getPropertiesFromJsonFile("set_name").sort()
        cards.map({ c -> c.setCode }).sort() == getPropertiesFromJsonFile("set").sort()
        cards.map({ c -> c.releasedAt }).sort() == getDatePropertiesFromJsonFile("released_at").sort()
        cards.map({ c -> c.price }).sort() == getPricesFromJsonFile().sort()
    }

    def "should return empty list when file is null"() {
        when:
        cards = cardMapper.fromJsonListFile(null)
        then:
        cards.isEmpty()
    }

    def "should return empty list when file refers to non existing file"() {
        given:
        setFilePath("src/test/resources/nonExistingFile.json")
        when:
        cards = cardMapper.fromJsonListFile(file)
        then:
        cards.isEmpty()
    }

    def "should return empty list when file refers to invalid cards file"() {
        given:
        setFilePath("src/test/resources/invalidCards.json")
        when:
        cards = cardMapper.fromJsonListFile(file)
        then:
        cards.isEmpty()
    }

    def "should return empty list when file refers to json with single object"() {
        given:
        setFilePath("src/test/resources/singleCard.json")
        when:
        cards = cardMapper.fromJsonListFile(file)
        then:
        cards.isEmpty()
    }

    def "should return empty list when filPath refers to unparseable file"() {
        given:
        setFilePath("src/test/resources/unparseableJson.json")
        when:
        cards = cardMapper.fromJsonListFile(file)
        then:
        cards.isEmpty()
    }

    private File setFilePath(String path) {
        file = FileUtils.getFile(path)
    }

    def getPricesFromJsonFile() {
        List<BigDecimal> pricesInEuro = List.empty();
        for (int i = 0; i < fileAsJson.length(); i++){
            JSONObject jsonobject = fileAsJson.getJSONObject(i);
            def priceInEuro = jsonobject.getJSONObject("prices").getString("eur")
            pricesInEuro = pricesInEuro.append(new BigDecimal(priceInEuro))
        }
        return pricesInEuro
    }

    def getPropertiesFromJsonFile(String property) {
        List<String> ids = List.empty();
        for (int i = 0; i < fileAsJson.length(); i++){
            ids = ids.append(fileAsJson.getJSONObject(i).getString(property))
        }
        return ids;
    }

    def getDatePropertiesFromJsonFile(String property) {
        List<LocalDate> ids = List.empty();
        for (int i = 0; i < fileAsJson.length(); i++){
            def dateAsString = fileAsJson.getJSONObject(i).getString(property)
            ids = ids.append(LocalDate.parse(dateAsString))
        }
        return ids;
    }

    def JSONArray parseReferenceFileToJson() {
        new JSONArray(FileUtils.readFileToString(FileUtils.getFile("src/test/resources/cardBulkData.json"), Charset.forName("UTF-8")))
    }

    //todo ustawic typowanie
}
