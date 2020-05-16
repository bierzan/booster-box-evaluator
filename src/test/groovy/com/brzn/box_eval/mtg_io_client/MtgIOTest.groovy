package com.brzn.box_eval.mtg_io_client

import com.brzn.box_eval.mtgIOclient.domain.SampleCardSets
import io.vavr.collection.List
import spock.lang.Specification

class MtgIOTest extends Specification implements SampleCardSets {

    MtgIOClient client = new MtgIOClient()
    MtgIO mtgIO = new MtgIO(client);

    def "should find CardSets by list of CardSets names"() {
        given:
//        def cardSetNames = List.of(sampleCommonSet.name).toSet()
        def cardSetNames = List.of("khans").toSet()
        client.findCardSetsByName(cardSetNames) >> [sampleCommonSet]
        when:
        def cardSets = mtgIO.findCardSetsByName(cardSetNames)
        then:
        cardSets.map({ set -> set.name })
                .contains(sampleCommonSet.name)
    }

    def json = '{\n' +
            '   "sets":[\n' +
            '      {\n' +
            '         "code":"KTK",\n' +
            '         "name":"Khans of Tarkir",\n' +
            '         "type":"expansion",\n' +
            '         "booster":[\n' +
            '            [\n' +
            '               "rare",\n' +
            '               "mythic rare"\n' +
            '            ],\n' +
            '            "uncommon",\n' +
            '            "uncommon",\n' +
            '            "uncommon",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "land",\n' +
            '            "marketing"\n' +
            '         ],\n' +
            '         "releaseDate":"2014-09-26",\n' +
            '         "block":"Khans of Tarkir",\n' +
            '         "onlineOnly":false\n' +
            '      },\n' +
            '      {\n' +
            '         "code":"PKTK",\n' +
            '         "name":"Khans of Tarkir Promos",\n' +
            '         "type":"promo",\n' +
            '         "releaseDate":"2014-09-27",\n' +
            '         "block":"Khans of Tarkir",\n' +
            '         "onlineOnly":false\n' +
            '      }\n' +
            '   ]\n' +
            '}'
}
//todo test na pusty set nazw

//{"sets":[{"code":"AMH1","name":"Modern Horizons Art Series","type":"memorabilia","releaseDate":"2019-06-05","onlineOnly":false},{"code":"MD1","name":"Modern Event Deck 2014","type":"box","releaseDate":"2014-05-30","onlineOnly":false},{"code":"MH1","name":"Modern Horizons","type":"draft_innovation","booster":[["rare","mythic rare"],"uncommon","uncommon","uncommon","common","common","common","common","common","common","common","common","common","common","snow land","token","full art print"],"releaseDate":"2019-06-14","onlineOnly":false},{"code":"MM2","name":"Modern Masters 2015","type":"masters","booster":[["rare","mythic rare"],"uncommon","uncommon","uncommon","common","common","common","common","common","common","common","common","common","common",["foil mythic rare","foil rare","foil uncommon","foil common"]],"releaseDate":"2015-05-22","onlineOnly":false},{"code":"MM3","name":"Modern Masters 2017","type":"masters","booster":[["rare","mythic rare"],"uncommon","uncommon","uncommon","common","common","common","common","common","common","common","common","common","common",["foil mythic rare","foil rare","foil uncommon","foil common"]],"releaseDate":"2017-03-17","onlineOnly":false},{"code":"MMA","name":"Modern Masters","type":"masters","booster":[["rare","mythic rare"],"uncommon","uncommon","uncommon","common","common","common","common","common","common","common","common","common","common",["foil mythic rare","foil rare","foil uncommon","foil common"]],"releaseDate":"2013-06-07","onlineOnly":false},{"code":"PMH1","name":"Modern Horizons Promos","type":"promo","releaseDate":"2019-06-14","onlineOnly":false}]}