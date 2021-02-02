package com.brzn.box_eval.card.domain

import com.brzn.box_eval.card.dto.CardDto
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.vavr.collection.List
import org.apache.commons.io.FileUtils
import org.assertj.core.internal.bytebuddy.utility.RandomString
import org.json.JSONArray
import org.json.JSONObject

import java.time.LocalDate

import static com.google.common.base.Charsets.UTF_8

class CardJsonTestUtil { //todo posprzatac

    public static final String referenceBulkDataFilePath = "src/test/resources/cardBulkData.json"
    public static final String temporaryJsonsPath = "src/test/resources/tempJsons/"

    def static getBulkDataReferenceFile() {
        return FileUtils.getFile(referenceBulkDataFilePath)
    }

    def static getTempSingleCardJsonFromBulkDataReferenceFile() {
        def card = mapJson2CardDtos(getBulkDataReferenceFile()).first()
        return writeAndReturnFile(mapToJsonCard(card), "singleCard")
    }

    def static getTempUnparseableJsonFile() {
        def path = temporaryJsonsPath + "unparseableJson.json"
        FileUtils.write(new File(path), "{can't be parsed}", UTF_8)
        return FileUtils.getFile(path)
    }

    def static deleteTemporaryJsons() {
        FileUtils.deleteQuietly(new File(temporaryJsonsPath))
    }

    def static getTempJsonCardsArrayFileFromCards(String jsonName, CardDto... cards) {
        JsonArray jsonArray = new JsonArray()
        cards.each { card ->
            JsonObject jsonCard = mapToJsonCard(card)
            jsonArray.add(jsonCard)
        }
        return writeAndReturnFile(jsonArray, jsonName)
    }

    def static getRandomJsonArray() {
        JsonArray jsonArray = new JsonArray()
        for (int i = 0; i < 3; i++) {
            JsonObject jsonObject = new JsonObject()
            jsonObject.addProperty(RandomString.make(), RandomString.make())
            jsonObject.addProperty(RandomString.make(), RandomString.make())
            jsonArray.add(jsonObject)
        }
        return writeAndReturnFile(jsonArray, "randomArray")
    }

    private static JsonObject mapToJsonCard(CardDto card) {
        JsonObject jsonCard = new JsonObject()
        jsonCard.addProperty("name", card.name)
        jsonCard.addProperty("released_at", card.releasedAt.toString())
        jsonCard.addProperty("set", card.setCode)
        jsonCard.addProperty("set_name", card.setName)
        jsonCard.addProperty("id", card.uuid)

        JsonObject priceJson = new JsonObject();
        priceJson.addProperty("eur", card.price.toString())
        jsonCard.add("prices", priceJson)
        jsonCard
    }

    private static File writeAndReturnFile(JsonElement jsonArray, String fileName) {
        FileUtils.write(new File(temporaryJsonsPath + fileName + ".json"), jsonArray.toString(), UTF_8)
        return FileUtils.getFile(temporaryJsonsPath + fileName + ".json")
    }

    def static List<CardDto> mapJson2CardDtos(File file) {
        def mapper = new ObjectMapper()
        try {
            return List.of(mapper.readValue(file, CardDto[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return List.empty();
        }
    }

    def static getPricesFromCardJsonFile(File jsonFile) {
        JSONArray jsonArray = parseReferenceFileToJson(jsonFile)
        List<BigDecimal> pricesInEuro = List.empty();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            def priceInEuro = jsonobject.getJSONObject("prices").getString("eur")
            pricesInEuro = pricesInEuro.append(new BigDecimal(priceInEuro))
        }
        return pricesInEuro
    }

    def static getPropertiesFromCardJsonFile(File jsonFile, String property) {
        JSONArray jsonArray = parseReferenceFileToJson(jsonFile)
        List<String> properties = List.empty();
        for (int i = 0; i < jsonArray.length(); i++) {
            properties = properties.append(jsonArray.getJSONObject(i).getString(property))
        }
        return properties;
    }

    def static getDatePropertiesFromJsonFile(File jsonFile, String property) {
        JSONArray jsonArray = parseReferenceFileToJson(jsonFile)

        List<LocalDate> dates = List.empty();
        for (int i = 0; i < jsonArray.length(); i++) {
            def dateAsString = jsonArray.getJSONObject(i).getString(property)
            dates = dates.append(LocalDate.parse(dateAsString))
        }
        return dates;
    }

    def static JSONArray parseReferenceFileToJson(File file) {
        return new JSONArray(FileUtils.readFileToString(file, UTF_8))
    }
}
