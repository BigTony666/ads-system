package com.bigtony.ad.index.adunit;

import com.bigtony.ad.index.adplan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitObject {
    
    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long planId;
    
    private AdPlanObject adPlanObject;
    
    void update(AdUnitObject newObject) {
        
        if (null != newObject.getUnitId()) {
            this.unitId = newObject.getUnitId();
        }
        if (null != newObject.getUnitStatus()) {
            this.unitStatus = newObject.getUnitStatus();
        }
        if (null != newObject.getPositionType()) {
            this.positionType = newObject.getPositionType();
        }
        if (null != planId) {
            this.planId = newObject.getPlanId();
        }
        if (null != newObject.getAdPlanObject()) {
            this.adPlanObject = newObject.getAdPlanObject();
        }
    }
    
    private static boolean isLaunchScreen(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.LAUNCHSCREEN) > 0;
    }
    
    private static boolean isPreMovie(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.PREMOVIE) > 0;
    }
    
    private static boolean isPreMovieMiddle(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.PREMOVIE_MIDDLE) > 0;
    }
    
    private static boolean isPreMoviePause(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.PREMOVIE_PAUSE) > 0;
    }
    
    private static boolean isPreMoviePost(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.PREMOVIE_POST) > 0;
    }
    
    private static boolean isNewsFeed(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.NEWSFEED) > 0;
    }
    
    public static boolean isAdSlotTypeOK(int adSlotType, int positionType) {
        
        switch (adSlotType) {
            case AdUnitConstants.POSITION_TYPE.LAUNCHSCREEN:
                return isLaunchScreen(positionType);
            case AdUnitConstants.POSITION_TYPE.PREMOVIE:
                return isPreMovie(positionType);
            case AdUnitConstants.POSITION_TYPE.PREMOVIE_MIDDLE:
                return isPreMovieMiddle(positionType);
            case AdUnitConstants.POSITION_TYPE.PREMOVIE_PAUSE:
                return isPreMoviePause(positionType);
            case AdUnitConstants.POSITION_TYPE.PREMOVIE_POST:
                return isPreMoviePost(positionType);
            case AdUnitConstants.POSITION_TYPE.NEWSFEED:
                return isNewsFeed(positionType);
            default:
                return false;
        }
    }
}

