package com.covid19.app

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.covid19.app.data.local.Covid19Database
import com.covid19.app.data.local.tables.patient.Patient
import com.covid19.app.data.local.tables.patient.PatientDao
import com.covid19.app.data.remote.models.StateData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class RoomDBTest {

    private lateinit var covid19Database: Covid19Database
    private lateinit var patientDao: PatientDao

    private val patient1 = Patient(
        1,
        "1",
        "20",
        "Student from Wuhan",
        "",
        "Recovered",
        "30/01/2020",
        "Thrissur",
        "Thrissur",
        "Kerala",
        "",
        "F",
        "India",
        "Travelled from Wuhan",
        "https://twitter.com/vijayanpinarayi/status/1222819465143832577",
        "https://weather.com/en-IN/india/news/news/2020-02-14-kerala-defeats-coronavirus-indias-three-covid-19-patients-successfully",
        "",
        "KL",
        "KL-TS-P1",
        "14/02/2020",
        "Imported"
    )

    private val patient2 = Patient(
        2,
        "2",
        "",
        "Student from Wuhan",
        "",
        "Recovered",
        "02/02/2020",
        "Alappuzha",
        "Alappuzha",
        "Kerala",
        "",
        "",
        "India",
        "Travelled from Wuhan",
        "https://www.indiatoday.in/india/story/kerala-reports-second-case-of-coronavirus-1642494-2020-02-02",
        "https://weather.com/en-IN/india/news/news/2020-02-14-kerala-defeats-coronavirus-indias-three-covid-19-patients-successfully",
        "",
        "KL",
        "KL-AL-P1",
        "14/02/2020",
        "Imported"
    )

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        covid19Database =
            Room.inMemoryDatabaseBuilder(appContext, Covid19Database::class.java).build()
        patientDao = covid19Database.patientDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        covid19Database.close()
    }

    @Test
    @Throws(Exception::class)
    fun patientTableInsertTest() {
        patientDao.insertAll(patient1, patient2)
        val all = patientDao.getAll()
        Assert.assertEquals(
            "Saved rows should be equal ",
            2,
            all.size
        )
        for (patient in all) {
            println(patient)
        }
    }

    @Test
    @Throws(Exception::class)
    fun patientTableSelectAllTest() {
        val all = patientDao.getAll()
        println("total patients = ${all.size}")
        Assert.assertNotNull(all)
    }


    val stateDataJson = "{\n" +
            "  \"Kerala\": {\n" +
            "    \"districtData\": {\n" +
            "      \"Thrissur\": {\n" +
            "        \"confirmed\": 13,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Alappuzha\": {\n" +
            "        \"confirmed\": 5,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Kasaragod\": {\n" +
            "        \"confirmed\": 167,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Pathanamthitta\": {\n" +
            "        \"confirmed\": 17,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Kannur\": {\n" +
            "        \"confirmed\": 77,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Ernakulam\": {\n" +
            "        \"confirmed\": 24,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Kottayam\": {\n" +
            "        \"confirmed\": 3,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Thiruvananthapuram\": {\n" +
            "        \"confirmed\": 14,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Idukki\": {\n" +
            "        \"confirmed\": 10,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Malappuram\": {\n" +
            "        \"confirmed\": 20,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Kozhikode\": {\n" +
            "        \"confirmed\": 16,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Palakkad\": {\n" +
            "        \"confirmed\": 8,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Wayanad\": {\n" +
            "        \"confirmed\": 3,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Kollam\": {\n" +
            "        \"confirmed\": 9,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"Delhi\": {\n" +
            "    \"districtData\": {\n" +
            "      \"East Delhi\": {\n" +
            "        \"confirmed\": 1,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"South West Delhi\": {\n" +
            "        \"confirmed\": 3,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"West Delhi\": {\n" +
            "        \"confirmed\": 2,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"North Delhi\": {\n" +
            "        \"confirmed\": 3,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"New Delhi\": {\n" +
            "        \"confirmed\": 5,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"South Delhi\": {\n" +
            "        \"confirmed\": 26,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"North East Delhi\": {\n" +
            "        \"confirmed\": 1,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"North West Delhi\": {\n" +
            "        \"confirmed\": 3,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      },\n" +
            "      \"Unknown\": {\n" +
            "        \"confirmed\": 1517,\n" +
            "        \"lastupdatedtime\": \"\",\n" +
            "        \"delta\": {\n" +
            "          \"confirmed\": 0\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}"


//    @Test
//    @Throws(Exception::class)
//    fun stateDataTableInsertTest() {
//        val listType = object : TypeToken<List<StateData>>() {}.type
//        val stateData = Gson().fromJson<List<StateData>>(stateDataJson, listType)
//        for (stateDatum in stateData)
//            covid19Database.stateDataDao().insert(stateDatum)
//
//    }
}