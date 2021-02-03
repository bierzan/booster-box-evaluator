package com.brzn.box_eval.adapter.card.provider

import com.brzn.box_eval.application.FileDownloader
import com.brzn.box_eval.infrastructure.client.Client
import spock.lang.Specification

import java.time.LocalDate

class DefaultCardJsonFileProviderTest extends Specification {
    def client = Mock(Client)
    def downloader = Mock(FileDownloader)
    def fileProvider = new DefaultCardJsonFileProvider(downloader, client)
    def fileToDownloadURL = new URL("http://url")
    def expectedFile = new File("file")

    def "should provide downloaded file"() {
        given:"url to download file"
        stubGettingUrl(fileToDownloadURL)
        and: "downloaded file"
        stubDownloadingFile(expectedFile)
        when:
        def file = fileProvider.getCardsJsonFileReleasedAfter(LocalDate.now())
        then:
        file == expectedFile
    }

    def "should provide downloaded file when requested date is null"() {
        given:"url to download file"
        stubGettingUrl(fileToDownloadURL)
        and: "downloaded file"
        stubDownloadingFile(expectedFile)
        when:
        def file = fileProvider.getCardsJsonFileReleasedAfter(null)
        then:
        file == expectedFile
    }

    def "should return null when downloader responded with null file"() {
        given:"url to download file"
        stubGettingUrl(fileToDownloadURL)
        and: "downloaded file"
        stubDownloadingFile(null)
        when:
        def file = fileProvider.getCardsJsonFileReleasedAfter(LocalDate.now())
        then:
        file == null
    }

    def "should return null when client didn't provide url"() {
        given:"url to download file"
        stubGettingUrl(null)
        when:
        def file = fileProvider.getCardsJsonFileReleasedAfter(LocalDate.now())
        then:
        file == null
    }

    private void stubGettingUrl(URL url) {
        client.getUrlForCardDateUpdatedAfter(_) >> url
    }

    private void stubDownloadingFile(File file) {
        downloader.getFileFromUrl(_ as URL, _ as String) >> file
    }
}
