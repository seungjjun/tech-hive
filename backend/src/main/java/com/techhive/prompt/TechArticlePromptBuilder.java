package com.techhive.prompt;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TechArticlePromptBuilder {

    private final String data;

    public String generatePrompt() {
        TechArticlePromptTemplate promptTemplate = new TechArticlePromptTemplate();
        List<TechArticlePromptTemplate.Example> examples = List.of(
            new TechArticlePromptTemplate.Example(1,
                """
                    토스 프론트엔드 챕터에서는 웹 성능을 최대한으로 높이기 위해 HTTP 캐시를 적극적으로 사용하고 있습니다.
                    캐시를 잘못 관리했을 때, 원하는 시점에 캐시가 사라지지 않을 수 있습니다. 필요 이상으로 HTTP 요청이 발생하기도 합니다.
                    HTTP 캐시를 효율적으로 관리하려면 Cache-Control 헤더를 섬세하게 조절해야 합니다.
                    토스 프론트엔드 챕터에서 다양한 생명 주기를 가지는 캐시를 다루면서 알게 된 노하우를 테크 블로그로 공유합니다.
                    캐시의 생명 주기 HTTP에서 리소스(Resource)란 웹 브라우저가 HTTP 요청으로 가져올 수 있는 모든 종류의 파일을 말합니다.
                    대표적으로 HTML, CSS, JS, 이미지, 비디오 파일 등이 리소스에 해당합니다. 웹 브라우저가 서버에서 지금까지 요청한 적이 없는 리소스를 가져오려고 할 때,
                    서버와 브라우저는 완전한 HTTP 요청/응답을 주고받습니다. HTTP 요청도 완전하고, 응답도 완전합니다.
                    이후 HTTP 응답에 포함된 Cache-Control 헤더에 따라 받은 리소스의 생명 주기가 결정됩니다.
                    캐시의 유효 기간: max-age 서버의 Cache-Control 헤더의 값으로 max-age=<seconds> 값을 지정하면, 이 리소스의 캐시가 유효한 시간은 <seconds> 초가 됩니다.
                    캐시의 유효 기간이 지나기 전 한 번 받아온 리소스의 유효 기간이 지나기 전이라면, 브라우저는 서버에 요청을 보내지 않고 디스크 또는 메모리에서만 캐시를 읽어와 계속 사용합니다.
                    메모리 캐시에서 불러온 HTTP 리소스 예를 들어, 위 개발자 도구 캡처와 같이 어떤 JavaScript 파일을 요청하는 경우를 가정합시다. 이 리소스가 가지는 Cache-Control 헤더 값은 max-age=31536000 이기 때문에,
                    이 리소스는 1년(31,536,000초)동안 캐시할 수 있습니다. 스크린샷에서는 유효한 캐시가 메모리에 남아 있기 때문에 (from memory cache) 라고 표기된 것을 확인할 수 있습니다.
                    """,
                """
                    {
                      "one_line_summary" : "토스 프론트엔드 챕터는 HTTP 캐시를 효과적으로 관리하여 웹 성능을 극대화하는 방법을 공유합니다.",
                      "three_line_summary" : [
                         "HTTP 캐시는 웹 성능을 높이는 중요한 요소로, 토스 프론트엔드 챕터에서는 이를 적극적으로 활용하고 있습니다.",
                         "캐시의 생명 주기를 관리하기 위해 Cache-Control 헤더를 섬세하게 조정하는 방법을 설명합니다.",
                         "특히, 리소스의 성격에 따라 캐시 설정을 다르게 하여 최적의 성능을 유지하는 전략을 제시합니다."
                      ],
                      "core_summary" : [
                          {
                             "title" : "HTTP 캐시의 중요성",
                             "summaries" : [
                                "웹 성능을 극대화하기 위해 HTTP 캐시를 적극적으로 활용하는 것이 중요합니다.",
                                "캐시를 잘못 관리하면 불필요한 HTTP 요청이 발생할 수 있으며, 이는 성능 저하로 이어질 수 있습니다."
                             ]
                          },
                          {
                             "title" : "Cache-Control 헤더의 역할",
                             "summaries" : [
                                "Cache-Control 헤더를 통해 캐시의 생명 주기를 조절할 수 있습니다.",
                                "HTTP에서 리소스(Resource)란 웹 브라우저가 HTTP 요청으로 가져올 수 있는 모든 종류의 파일을 말합니다.",
                                "max-age 값을 설정하여 리소스의 캐시 유효 기간을 정의하고, 이를 통해 브라우저가 서버에 요청을 보내지 않고도 캐시를 사용할 수 있도록 합니다.",
                             ]
                          }
                      ],
                      "categories" : {
                         "parent_category" : "Frontend",
                         "child_categories" : [
                            "JavaScript",
                            "HTTP"
                         ]
                      }
                    }
                    """)
        );

        final String instructionPrompt = promptTemplate.generatePrompt(examples, data);
        log.debug("\n[[INSTRUCTION PROMPT]]\n{}", instructionPrompt);
        return instructionPrompt;
    }
}
