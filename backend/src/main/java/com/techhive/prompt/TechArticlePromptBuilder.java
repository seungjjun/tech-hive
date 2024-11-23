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
                    최근 클라우드 컴퓨팅 기술이 급격히 발전하면서, 많은 기업들이 클라우드 기반의 인프라를 도입하고 있습니다.
                    특히, 마이크로서비스 아키텍처와 컨테이너 기술의 결합은 애플리케이션의 확장성과 유연성을 크게 향상시키고 있습니다.
                    그러나 이러한 기술의 도입에는 보안 및 데이터 관리 측면에서의 새로운 도전 과제도 함께 따라오고 있습니다.
                    이에 따라 기업들은 효과적인 클라우드 보안 전략을 수립하고, 데이터 거버넌스를 강화하는 방안을 모색하고 있습니다.
                    """,
                """
                    {
                      "one_line_summary" : "클라우드 컴퓨팅의 발전과 마이크로서비스, 컨테이너 기술의 도입이 기업의 인프라 유연성을 향상시키지만 보안 및 데이터 관리의 도전 과제를 동반합니다.",
                      "three_line_summary" : "- 클라우드 컴퓨팅 기술의 급속한 발전으로 많은 기업들이 클라우드 기반 인프라를 채택하고 있습니다.
                      - 하지만 보안과 데이터 관리 측면에서 새로운 도전 과제가 발생하여, 기업들은 이를 해결하기 위한 전략을 마련하고 있습니다.",
                      "core_summary" : "클라우드 컴퓨팅 기술의 발전
                      - 클라우드 컴퓨팅 기술이 빠르게 발전하면서, 많은 기업들이 클라우드 기반의 인프라를 도입하고 있습니다. 이는 비용 절감과 유연한 자원 관리를 가능하게 합니다.
                      마이크로서비스 아키텍처와 컨테이너 기술의 결합
                      마이크로서비스 아키텍처와 컨테이너 기술의 결합은 애플리케이션의 확장성과 유연성을 크게 향상시킵니다. 이를 통해 개발 및 배포 과정이 간소화되고, 시스템의 유지보수가 용이해집니다.
                      보안 및 데이터 관리의 새로운 도전 과제
                      클라우드 인프라의 도입과 기술의 발전에 따라 보안 및 데이터 관리 측면에서 새로운 도전 과제가 발생하고 있습니다. 기업들은 이를 해결하기 위해 효과적인 클라우드 보안 전략을 수립하고, 데이터 거버넌스를 강화하는 방안을 모색하고 있습니다.
                      효과적인 클라우드 보안 전략 수립
                      보안 위협이 증가함에 따라, 기업들은 클라우드 환경에서의 보안을 강화하기 위한 전략을 수립하고 있습니다. 이는 데이터 암호화, 접근 제어, 모니터링 등을 포함합니다.
                      "
                    }
                    """)
        );

        final String instructionPrompt = promptTemplate.generatePrompt(data);
        log.debug("\n[[INSTRUCTION PROMPT]]\n{}", instructionPrompt);
        return instructionPrompt;
    }
}
