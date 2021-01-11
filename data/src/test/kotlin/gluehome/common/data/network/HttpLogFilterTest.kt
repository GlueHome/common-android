package gluehome.common.data.network

import org.amshove.kluent.shouldBe
import org.junit.Test

class HttpLogFilterTest {

    private val filter = HttpLogFilter()

    @Test
    fun `Text that should be removed`() {
        filter.shouldBeRemoved("alt-svc: clear") shouldBe true
        filter.shouldBeRemoved("content-type: application/json; charset=utf-8") shouldBe true
        filter.shouldBeRemoved("via: 1.1 google") shouldBe true
        filter.shouldBeRemoved("date: Wed, 08 Jan 2020 23:44:24 GMT") shouldBe true
        filter.shouldBeRemoved("x-cloud-trace-context: 8e3c87937a2a92d92c297231d965f6b8/3841932400416855268;o=1") shouldBe true
        filter.shouldBeRemoved("If-Modified-Since: Wed, 08 Jan 2020 23:44:24 GMT") shouldBe true
        filter.shouldBeRemoved("Host: mobile.gluehome.com") shouldBe true
        filter.shouldBeRemoved("Accept-Encoding: gzip") shouldBe true
        filter.shouldBeRemoved("User-Agent: Glue/v1.0.3-build-561-(2020-01-08)-20b3df3 (Android Pie 9.0, API 28; ONEPLUS A6010)") shouldBe true
        filter.shouldBeRemoved("Accept: application/json") shouldBe true
        filter.shouldBeRemoved("Connection: Keep-Alive") shouldBe true
        filter.shouldBeRemoved("content-length: 0") shouldBe true
        filter.shouldBeRemoved("server: openresty/1.15.8.1") shouldBe true
        filter.shouldBeRemoved("content-encoding: gzip") shouldBe true
        filter.shouldBeRemoved("strict-transport-security: max-age=15724800; includeSubDomains") shouldBe true
        filter.shouldBeRemoved("vary: Accept-Encoding") shouldBe true
        filter.shouldBeRemoved("Host: mobile-dev.gluehome.net") shouldBe true
        filter.shouldBeRemoved("cf-ray: 58b25308") shouldBe true
        filter.shouldBeRemoved("nel: {\"report_to\":\"cf-nel\",\"max_age\":604800}") shouldBe true
        filter.shouldBeRemoved("report-to: {\"endpoints\":[{\"url\":\"https:\\/\\/a.nel.cloudflare.com\\/report?lkg-colo=21&lkg-time=1601659851\"}],\"group\":\"cf-nel\",\"max_age\":604800}") shouldBe true
        filter.shouldBeRemoved("<-- END HTTP") shouldBe true
    }

    @Test
    fun `Text that should be kept`() {
        filter.shouldBeRemoved("glue-correlation-id: a24a80ac") shouldBe false
        filter.shouldBeRemoved("<-- 201 https://mobile.gluehome.com/api/v1/locks/3a4858eb-5268-44fc-bd60-944adcc3ba07/events (161ms)") shouldBe false
        filter.shouldBeRemoved("<-- 200 https://mobile.gluehome.com/api/v1/locks/3a4858eb-5268-44fc-bd60-944adcc3ba07 (91ms)") shouldBe false
        filter.shouldBeRemoved("--> POST https://mobile.gluehome.com/api/v1/locks/3a4858eb-5268-44fc-bd60-944adcc3ba07/events h2") shouldBe false
        filter.shouldBeRemoved("--> GET https://mobile.gluehome.com/api/v1/locks/3a4858eb-5268-44fc-bd60-944adcc3ba07 h2") shouldBe false
        filter.shouldBeRemoved("WWW-Authenticate: Bearer realm=\"example\"") shouldBe false
        filter.shouldBeRemoved("{\"id\":\"029cdccd-5056-4303-916a-adbdd17d7bbb\",\"name\":\"Cesars v5 lock 👩‍❤️‍💋‍👨\",\"isNotif") shouldBe false
        filter.shouldBeRemoved("[{\"id\":\"2091c1fa-efe9-4dc2-ba8c-61a8179ff836\",\"eventTime\":\"2020-04-28T17:16:46") shouldBe false
    }
}