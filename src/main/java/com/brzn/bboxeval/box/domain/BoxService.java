package com.brzn.bboxeval.box.domain;

import com.brzn.bboxeval.box.dto.BoxDto;
import io.vavr.collection.List;

class BoxService {

    private BoxRepository repository;

    BoxService(BoxRepository repository) {
        this.repository = repository;
    }


    List<BoxDto> searchNew() {
        return null;
    }
}

/*todo
update:
-sprawdz ostatnia box wg ostatniej daty <<box getLast
-pobierz karty z data nowsza <<RestClient
-pobierz dane o boxach w ktorych te karty wystapily <<restClient getBoxesReleasedAfter(date)
-pobierz te boxy <<restClitent getBoxData
-zmapuj na Box i zapisz do bazy*/

