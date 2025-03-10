package com.portal.asciiproject.ui.evkur

import ParameterTabs
import TabData
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.portal.asciiproject.R
import com.portal.asciiproject.compose.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EvkurTestFragment : BaseFragment(R.layout.fragment_evkur_test) {

    private val evkurTestViewModel: EvkurTestViewModel by viewModels()

    override fun observeVariables() {
    }

    override fun setupComposeUI(composeView: ComposeView) {
        composeView.setContent {
            val jsonString = getJsonFromService()
            val tabs = parseJsonToTabs(jsonString)
            ParameterTabs(tabs = tabs)
        }
    }

    private fun getJsonFromService(): String {
        // Simulate fetching JSON from a service
        return """
            [
                {
                    "tabTitle": "Genel Bilgiler",
                    "subheadings": [
                        {
                            "subheadingName": "Kişisel Bilgiler",
                            "contents": [
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Soyad",
                                    "contentDetails": ["Döner"]
                                }
                            ]
                        },
                        {
                            "subheadingName": "İletişim Bilgileri",
                            "contents": [
                                {
                                    "contentName": "Telefon Numarası",
                                    "contentDetails": ["05462135454", "03526455455"]
                                }
                            ]
                        }
                    ]
                },
                {
                    "tabTitle": "Genel Bilgiler",
                    "subheadings": [
                        {
                            "subheadingName": "Kişisel Bilgiler",
                            "contents": [
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Soyad",
                                    "contentDetails": ["Döner"]
                                }
                            ]
                        },
                        {
                            "subheadingName": "İletişim Bilgileri",
                            "contents": [
                                {
                                    "contentName": "Telefon Numarası",
                                    "contentDetails": ["05462135454", "03526455455"]
                                }
                            ]
                        }
                    ]
                },
                {
                    "tabTitle": "Genel Bilgiler",
                    "subheadings": [
                        {
                            "subheadingName": "Kişisel Bilgiler",
                            "contents": [
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                  {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                
                                {
                                    "contentName": "Soyad",
                                    "contentDetails": ["Döner"]
                                }
                            ]
                        },
                        {
                            "subheadingName": "Kişisel Bilgiler",
                            "contents": [
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Soyad",
                                    "contentDetails": ["Döner"]
                                }
                            ]
                        },{
                            "subheadingName": "Kişisel Bilgiler",
                            "contents": [
                                {
                                    "contentName": "Ad",
                                    "contentDetails": ["Gürkan"]
                                },
                                {
                                    "contentName": "Soyad",
                                    "contentDetails": ["Döner"]
                                }
                            ]
                        },
                        {
                            "subheadingName": "İletişim Bilgileri",
                            "contents": [
                                {
                                    "contentName": "Telefon Numarası",
                                    "contentDetails": ["05462135454", "03526455455"]
                                }
                            ]
                        }
                    ]
                },
                {
                    "tabTitle": "Adres Bilgileri",
                    "subheadings": [
                        {
                            "subheadingName": "Geçersiz adresler",
                            "contents": [
                                {
                                    "contentName": "İş adresi",
                                    "contentDetails": ["evkur halkalı genel merkez"]
                                }
                            ]
                        },
                        {
                            "subheadingName": "Adresler",
                            "contents": [
                                {
                                    "contentName": "İş adresi",
                                    "contentDetails": ["evkur halkalı genel merkez"]
                                }
                            ]
                        }
                    ]
                }
            ]
        """
    }

    private fun parseJsonToTabs(jsonString: String): List<TabData> {
        val gson = Gson()
        val listType = object : TypeToken<List<TabData>>() {}.type
        return gson.fromJson(jsonString, listType)
    }


}