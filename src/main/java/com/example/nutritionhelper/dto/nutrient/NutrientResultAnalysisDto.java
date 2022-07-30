package com.example.nutritionhelper.dto.nutrient;


import com.example.nutritionhelper.domain.nutrient.Nutrient;
import com.example.nutritionhelper.domain.userGroupNutrient.UserGroupNutrient;
import com.example.nutritionhelper.dto.nutrientAnalysis.NutrientAnalysisDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NutrientResultAnalysisDto {

    Nutrient nutrient;
    Float averageAmount;
    Float recommendAmount;
    Float enoughAmount;
    Float maximumAmount;
    Float amount;
    // 초과 여부
    int type;

    Float averageGaugeNum;
    Float recommendGaugeNum;
    Float enoughGaugeNum;
    Float maximumGaugeNum;
    Float amountGaugeNum;


    public NutrientResultAnalysisDto(UserGroupNutrient userGroupNutrient,
                                     NutrientAnalysisDto nutrientAnalysisDto,
                                     int type,
                                     Float averageGaugeNum,
                                     Float recommendGaugeNum,
                                     Float enoughGaugeNum,
                                     Float maximumGaugeNum,
                                     Float amountGaugeNum){
        this.nutrient = userGroupNutrient.getNutrient();
        this.averageAmount = userGroupNutrient.getAverageAmount();
        this.recommendAmount = userGroupNutrient.getRecommendAmount();
        this.enoughAmount = userGroupNutrient.getEnoughAmount();
        this.maximumAmount = userGroupNutrient.getMaximumAmount();
        this.amount = nutrientAnalysisDto.getAmount();
        this.type = type;
        this.averageGaugeNum = averageGaugeNum;
        this.recommendGaugeNum = recommendGaugeNum;
        this.enoughGaugeNum = enoughGaugeNum;
        this.maximumGaugeNum = maximumGaugeNum;
        this.amountGaugeNum = amountGaugeNum;

    }
    public NutrientResultAnalysisDto(UserGroupNutrient userGroupNutrient
            , NutrientAnalysisDto nutrientAnalysisDto){
        this.nutrient = userGroupNutrient.getNutrient();
        this.averageAmount = userGroupNutrient.getAverageAmount();
        this.recommendAmount = userGroupNutrient.getRecommendAmount();
        this.enoughAmount = userGroupNutrient.getEnoughAmount();
        this.maximumAmount = userGroupNutrient.getMaximumAmount();
        this.amount = nutrientAnalysisDto.getAmount();

        int n = 0;
        // 1. average만 존재하는 경우
        // ENG
        if(userGroupNutrient.getAverageAmount()!=null&&userGroupNutrient.getRecommendAmount()==null&&userGroupNutrient.getEnoughAmount()==null
                &&userGroupNutrient.getMaximumAmount()==null){
            n=1;
        }
        // 2. average, recommend만 존재하는 경우
        // recommend보다 적으면 deficiency(0), 많으면 excess(1)
        else if(userGroupNutrient.getAverageAmount()!=null&&userGroupNutrient.getRecommendAmount()!=null&&userGroupNutrient.getEnoughAmount()==null
                &&userGroupNutrient.getMaximumAmount()==null){
            n=2;
        }
        // 3. enough만 존재하는 경우
        // enough 넘으면 많으면 excess(1)
        else if(userGroupNutrient.getAverageAmount()==null&&userGroupNutrient.getRecommendAmount()==null&&userGroupNutrient.getEnoughAmount()!=null
                &&userGroupNutrient.getMaximumAmount()==null){
            n=3;
        }
        // 4. average, recommend, maximum만 존재하는 경우
        // recommend보다 적으면 deficiency(0), maximum보다 많으면 excess(1)
        else if(userGroupNutrient.getAverageAmount()!=null&&userGroupNutrient.getRecommendAmount()!=null&&userGroupNutrient.getEnoughAmount()==null
                &&userGroupNutrient.getMaximumAmount()!=null){
            n=4;
        }
        // 5. enough, maximum만 존재하는 경우
        // maximum보다 많으면 excess(1)
        else if(userGroupNutrient.getAverageAmount()==null&&userGroupNutrient.getRecommendAmount()==null&&userGroupNutrient.getEnoughAmount()!=null
                &&userGroupNutrient.getMaximumAmount()!=null){
            n=5;
        }

        switch(n) {
            case 0:
                throw new RuntimeException("분석 오류1");
            case 1:
                this.type=-1;
            case 2:
                // deficiency(0)
                if(userGroupNutrient.getRecommendAmount()>nutrientAnalysisDto.getAmount()){
                    this.type=0;
                }
                // excess(1)
                else if(userGroupNutrient.getRecommendAmount()<nutrientAnalysisDto.getAmount()){
                    this.type=1;
                }else{
                    this.type=-1;
                }
                break;
            case 3:
                // excess(1)
                if(userGroupNutrient.getEnoughAmount()<nutrientAnalysisDto.getAmount()){
                    this.type=1;
                }else{
                    this.type=-1;
                }
                break;
            case 4:
                if(userGroupNutrient.getRecommendAmount()>nutrientAnalysisDto.getAmount()){
                    this.type=0;
                }
                // excess(1)
                else if(userGroupNutrient.getMaximumAmount()<nutrientAnalysisDto.getAmount()){
                    this.type=1;
                }else{
                    this.type=-1;
                }
                break;
            case 5:
                if(userGroupNutrient.getMaximumAmount()<nutrientAnalysisDto.getAmount()){
                    this.type=1;
                }else{
                    this.type=-1;
                }
                break;
            default:
                break;
        }
    }

}
