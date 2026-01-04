package me.jing.kecheng.utils.http.gupaiBean;

import java.util.List;

public class Api2Response {


    private int code;
    private String message;
    private DataDTO data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        private int id;
        private String userId;
        private String streamId;
        private String title;
        private String description;
        private String rtmpPlayUrl;
        private String flvPlayUrl;
        private String hlsPlayUrl;
        private String status;
        private int likeCount;
        private int watchCount;
        private boolean isTop;
        private String cover;
        private String authorCover;
        private String lastStartAt;
        private String firstStartAt;
        private int sort;
        private int height;
        private int width;
        private boolean isThirdParty;
        private int show;
        private String type;
        private Object shareUrl;
        private boolean tapeEnabled;
        private int fakeWatchCount;
        private int channel;
        private boolean isTestPlay;
        private boolean hasTape;
        private boolean disableDelay;
        private int delayTime;
        private int originHeight;
        private int originWidth;
        private Object lockEndAt;
        private boolean isPublic;
        private String rectangleCover;
        private List<?> recommendAlbums;
        private boolean hasRobotComment;
        private String shareTitle;
        private String shareContent;
        private Object shareCover;
        private boolean allowPushFreeGift;
        private boolean mixMicPermission;
        private List<String> liveType;
        private boolean defaultQuickGift;
        private boolean hasQuickGift;
        private int quickGiftId;
        private boolean allowLiveCommerce;
        private int paidLiveId;
        private int paidLiveType;
        private boolean liveConnectPermission;
        private boolean authorWishSwitch;
        private Object connectionId;
        private String connectionStatus;
        private boolean connectionEnabled;
        private Object currentLiveType;
        private boolean seeTapeWhenFinish;
        private boolean barragePermission;
        private boolean downloadStatus;
        private int tapeRetentionDate;
        private boolean isPaidLive;
        private TapeDTO tape;
        private String acceleratePlayUrl;
        private String audioPlayUrl;
        private String roomType;
        private int displayMode;
        private int onlineCount;
        private List<BarrageColorsDTO> barrageColors;
        private int contentCount;
        private boolean hasProgram;
        private ProgramDTO program;
        private AuthorDTO author;
        private QuickGiftDTO quickGift;
        private FansClubDTO fansClub;
        private CurUserDTO curUser;
        private UserDTO user;
        private int fansCount;
        private int giftCount;
        private int followCount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getStreamId() {
            return streamId;
        }

        public void setStreamId(String streamId) {
            this.streamId = streamId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRtmpPlayUrl() {
            return rtmpPlayUrl;
        }

        public void setRtmpPlayUrl(String rtmpPlayUrl) {
            this.rtmpPlayUrl = rtmpPlayUrl;
        }

        public String getFlvPlayUrl() {
            return flvPlayUrl;
        }

        public void setFlvPlayUrl(String flvPlayUrl) {
            this.flvPlayUrl = flvPlayUrl;
        }

        public String getHlsPlayUrl() {
            return hlsPlayUrl;
        }

        public void setHlsPlayUrl(String hlsPlayUrl) {
            this.hlsPlayUrl = hlsPlayUrl;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getWatchCount() {
            return watchCount;
        }

        public void setWatchCount(int watchCount) {
            this.watchCount = watchCount;
        }

        public boolean isIsTop() {
            return isTop;
        }

        public void setIsTop(boolean isTop) {
            this.isTop = isTop;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getAuthorCover() {
            return authorCover;
        }

        public void setAuthorCover(String authorCover) {
            this.authorCover = authorCover;
        }

        public String getLastStartAt() {
            return lastStartAt;
        }

        public void setLastStartAt(String lastStartAt) {
            this.lastStartAt = lastStartAt;
        }

        public String getFirstStartAt() {
            return firstStartAt;
        }

        public void setFirstStartAt(String firstStartAt) {
            this.firstStartAt = firstStartAt;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public boolean isIsThirdParty() {
            return isThirdParty;
        }

        public void setIsThirdParty(boolean isThirdParty) {
            this.isThirdParty = isThirdParty;
        }

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(Object shareUrl) {
            this.shareUrl = shareUrl;
        }

        public boolean isTapeEnabled() {
            return tapeEnabled;
        }

        public void setTapeEnabled(boolean tapeEnabled) {
            this.tapeEnabled = tapeEnabled;
        }

        public int getFakeWatchCount() {
            return fakeWatchCount;
        }

        public void setFakeWatchCount(int fakeWatchCount) {
            this.fakeWatchCount = fakeWatchCount;
        }

        public int getChannel() {
            return channel;
        }

        public void setChannel(int channel) {
            this.channel = channel;
        }

        public boolean isIsTestPlay() {
            return isTestPlay;
        }

        public void setIsTestPlay(boolean isTestPlay) {
            this.isTestPlay = isTestPlay;
        }

        public boolean isHasTape() {
            return hasTape;
        }

        public void setHasTape(boolean hasTape) {
            this.hasTape = hasTape;
        }

        public boolean isDisableDelay() {
            return disableDelay;
        }

        public void setDisableDelay(boolean disableDelay) {
            this.disableDelay = disableDelay;
        }

        public int getDelayTime() {
            return delayTime;
        }

        public void setDelayTime(int delayTime) {
            this.delayTime = delayTime;
        }

        public int getOriginHeight() {
            return originHeight;
        }

        public void setOriginHeight(int originHeight) {
            this.originHeight = originHeight;
        }

        public int getOriginWidth() {
            return originWidth;
        }

        public void setOriginWidth(int originWidth) {
            this.originWidth = originWidth;
        }

        public Object getLockEndAt() {
            return lockEndAt;
        }

        public void setLockEndAt(Object lockEndAt) {
            this.lockEndAt = lockEndAt;
        }

        public boolean isIsPublic() {
            return isPublic;
        }

        public void setIsPublic(boolean isPublic) {
            this.isPublic = isPublic;
        }

        public String getRectangleCover() {
            return rectangleCover;
        }

        public void setRectangleCover(String rectangleCover) {
            this.rectangleCover = rectangleCover;
        }

        public List<?> getRecommendAlbums() {
            return recommendAlbums;
        }

        public void setRecommendAlbums(List<?> recommendAlbums) {
            this.recommendAlbums = recommendAlbums;
        }

        public boolean isHasRobotComment() {
            return hasRobotComment;
        }

        public void setHasRobotComment(boolean hasRobotComment) {
            this.hasRobotComment = hasRobotComment;
        }

        public String getShareTitle() {
            return shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }

        public String getShareContent() {
            return shareContent;
        }

        public void setShareContent(String shareContent) {
            this.shareContent = shareContent;
        }

        public Object getShareCover() {
            return shareCover;
        }

        public void setShareCover(Object shareCover) {
            this.shareCover = shareCover;
        }

        public boolean isAllowPushFreeGift() {
            return allowPushFreeGift;
        }

        public void setAllowPushFreeGift(boolean allowPushFreeGift) {
            this.allowPushFreeGift = allowPushFreeGift;
        }

        public boolean isMixMicPermission() {
            return mixMicPermission;
        }

        public void setMixMicPermission(boolean mixMicPermission) {
            this.mixMicPermission = mixMicPermission;
        }

        public List<String> getLiveType() {
            return liveType;
        }

        public void setLiveType(List<String> liveType) {
            this.liveType = liveType;
        }

        public boolean isDefaultQuickGift() {
            return defaultQuickGift;
        }

        public void setDefaultQuickGift(boolean defaultQuickGift) {
            this.defaultQuickGift = defaultQuickGift;
        }

        public boolean isHasQuickGift() {
            return hasQuickGift;
        }

        public void setHasQuickGift(boolean hasQuickGift) {
            this.hasQuickGift = hasQuickGift;
        }

        public int getQuickGiftId() {
            return quickGiftId;
        }

        public void setQuickGiftId(int quickGiftId) {
            this.quickGiftId = quickGiftId;
        }

        public boolean isAllowLiveCommerce() {
            return allowLiveCommerce;
        }

        public void setAllowLiveCommerce(boolean allowLiveCommerce) {
            this.allowLiveCommerce = allowLiveCommerce;
        }

        public int getPaidLiveId() {
            return paidLiveId;
        }

        public void setPaidLiveId(int paidLiveId) {
            this.paidLiveId = paidLiveId;
        }

        public int getPaidLiveType() {
            return paidLiveType;
        }

        public void setPaidLiveType(int paidLiveType) {
            this.paidLiveType = paidLiveType;
        }

        public boolean isLiveConnectPermission() {
            return liveConnectPermission;
        }

        public void setLiveConnectPermission(boolean liveConnectPermission) {
            this.liveConnectPermission = liveConnectPermission;
        }

        public boolean isAuthorWishSwitch() {
            return authorWishSwitch;
        }

        public void setAuthorWishSwitch(boolean authorWishSwitch) {
            this.authorWishSwitch = authorWishSwitch;
        }

        public Object getConnectionId() {
            return connectionId;
        }

        public void setConnectionId(Object connectionId) {
            this.connectionId = connectionId;
        }

        public String getConnectionStatus() {
            return connectionStatus;
        }

        public void setConnectionStatus(String connectionStatus) {
            this.connectionStatus = connectionStatus;
        }

        public boolean isConnectionEnabled() {
            return connectionEnabled;
        }

        public void setConnectionEnabled(boolean connectionEnabled) {
            this.connectionEnabled = connectionEnabled;
        }

        public Object getCurrentLiveType() {
            return currentLiveType;
        }

        public void setCurrentLiveType(Object currentLiveType) {
            this.currentLiveType = currentLiveType;
        }

        public boolean isSeeTapeWhenFinish() {
            return seeTapeWhenFinish;
        }

        public void setSeeTapeWhenFinish(boolean seeTapeWhenFinish) {
            this.seeTapeWhenFinish = seeTapeWhenFinish;
        }

        public boolean isBarragePermission() {
            return barragePermission;
        }

        public void setBarragePermission(boolean barragePermission) {
            this.barragePermission = barragePermission;
        }

        public boolean isDownloadStatus() {
            return downloadStatus;
        }

        public void setDownloadStatus(boolean downloadStatus) {
            this.downloadStatus = downloadStatus;
        }

        public int getTapeRetentionDate() {
            return tapeRetentionDate;
        }

        public void setTapeRetentionDate(int tapeRetentionDate) {
            this.tapeRetentionDate = tapeRetentionDate;
        }

        public boolean isIsPaidLive() {
            return isPaidLive;
        }

        public void setIsPaidLive(boolean isPaidLive) {
            this.isPaidLive = isPaidLive;
        }

        public TapeDTO getTape() {
            return tape;
        }

        public void setTape(TapeDTO tape) {
            this.tape = tape;
        }

        public String getAcceleratePlayUrl() {
            return acceleratePlayUrl;
        }

        public void setAcceleratePlayUrl(String acceleratePlayUrl) {
            this.acceleratePlayUrl = acceleratePlayUrl;
        }

        public String getAudioPlayUrl() {
            return audioPlayUrl;
        }

        public void setAudioPlayUrl(String audioPlayUrl) {
            this.audioPlayUrl = audioPlayUrl;
        }

        public String getRoomType() {
            return roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }

        public int getDisplayMode() {
            return displayMode;
        }

        public void setDisplayMode(int displayMode) {
            this.displayMode = displayMode;
        }

        public int getOnlineCount() {
            return onlineCount;
        }

        public void setOnlineCount(int onlineCount) {
            this.onlineCount = onlineCount;
        }

        public List<BarrageColorsDTO> getBarrageColors() {
            return barrageColors;
        }

        public void setBarrageColors(List<BarrageColorsDTO> barrageColors) {
            this.barrageColors = barrageColors;
        }

        public int getContentCount() {
            return contentCount;
        }

        public void setContentCount(int contentCount) {
            this.contentCount = contentCount;
        }

        public boolean isHasProgram() {
            return hasProgram;
        }

        public void setHasProgram(boolean hasProgram) {
            this.hasProgram = hasProgram;
        }

        public ProgramDTO getProgram() {
            return program;
        }

        public void setProgram(ProgramDTO program) {
            this.program = program;
        }

        public AuthorDTO getAuthor() {
            return author;
        }

        public void setAuthor(AuthorDTO author) {
            this.author = author;
        }

        public QuickGiftDTO getQuickGift() {
            return quickGift;
        }

        public void setQuickGift(QuickGiftDTO quickGift) {
            this.quickGift = quickGift;
        }

        public FansClubDTO getFansClub() {
            return fansClub;
        }

        public void setFansClub(FansClubDTO fansClub) {
            this.fansClub = fansClub;
        }

        public CurUserDTO getCurUser() {
            return curUser;
        }

        public void setCurUser(CurUserDTO curUser) {
            this.curUser = curUser;
        }

        public UserDTO getUser() {
            return user;
        }

        public void setUser(UserDTO user) {
            this.user = user;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }

        public int getGiftCount() {
            return giftCount;
        }

        public void setGiftCount(int giftCount) {
            this.giftCount = giftCount;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public static class TapeDTO {
            private int id;
            private String title;
            private String updatedAt;
            private int startTime;
            private int endTime;
            private String videoUrl;
            private boolean isPaidLive;
            private int paidLiveId;
            private int height;
            private int width;
            private int originHeight;
            private int originWidth;
            private boolean isPublished;
            private int detailId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public int getEndTime() {
                return endTime;
            }

            public void setEndTime(int endTime) {
                this.endTime = endTime;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }

            public boolean isIsPaidLive() {
                return isPaidLive;
            }

            public void setIsPaidLive(boolean isPaidLive) {
                this.isPaidLive = isPaidLive;
            }

            public int getPaidLiveId() {
                return paidLiveId;
            }

            public void setPaidLiveId(int paidLiveId) {
                this.paidLiveId = paidLiveId;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getOriginHeight() {
                return originHeight;
            }

            public void setOriginHeight(int originHeight) {
                this.originHeight = originHeight;
            }

            public int getOriginWidth() {
                return originWidth;
            }

            public void setOriginWidth(int originWidth) {
                this.originWidth = originWidth;
            }

            public boolean isIsPublished() {
                return isPublished;
            }

            public void setIsPublished(boolean isPublished) {
                this.isPublished = isPublished;
            }

            public int getDetailId() {
                return detailId;
            }

            public void setDetailId(int detailId) {
                this.detailId = detailId;
            }
        }

        public static class ProgramDTO {
        }

        public static class AuthorDTO {
            private int id;
            private String userId;
            private int rewardPermission;
            private String title;
            private String practiceNo;
            private String vipCertification;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getRewardPermission() {
                return rewardPermission;
            }

            public void setRewardPermission(int rewardPermission) {
                this.rewardPermission = rewardPermission;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPracticeNo() {
                return practiceNo;
            }

            public void setPracticeNo(String practiceNo) {
                this.practiceNo = practiceNo;
            }

            public String getVipCertification() {
                return vipCertification;
            }

            public void setVipCertification(String vipCertification) {
                this.vipCertification = vipCertification;
            }
        }

        public static class QuickGiftDTO {
            private int id;
            private String createdAt;
            private String updatedAt;
            private int giftType;
            private String type;
            private String targetType;
            private Object target;
            private String name;
            private String image;
            private int price;
            private int lockLevel;
            private String animation;
            private int sort;
            private boolean onOff;
            private String editAdmin;
            private boolean isFullScreenAnimation;
            private String fullScreenAnimationUrl;
            private String fullScreenAnimationZipUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public int getGiftType() {
                return giftType;
            }

            public void setGiftType(int giftType) {
                this.giftType = giftType;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTargetType() {
                return targetType;
            }

            public void setTargetType(String targetType) {
                this.targetType = targetType;
            }

            public Object getTarget() {
                return target;
            }

            public void setTarget(Object target) {
                this.target = target;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getLockLevel() {
                return lockLevel;
            }

            public void setLockLevel(int lockLevel) {
                this.lockLevel = lockLevel;
            }

            public String getAnimation() {
                return animation;
            }

            public void setAnimation(String animation) {
                this.animation = animation;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public boolean isOnOff() {
                return onOff;
            }

            public void setOnOff(boolean onOff) {
                this.onOff = onOff;
            }

            public String getEditAdmin() {
                return editAdmin;
            }

            public void setEditAdmin(String editAdmin) {
                this.editAdmin = editAdmin;
            }

            public boolean isIsFullScreenAnimation() {
                return isFullScreenAnimation;
            }

            public void setIsFullScreenAnimation(boolean isFullScreenAnimation) {
                this.isFullScreenAnimation = isFullScreenAnimation;
            }

            public String getFullScreenAnimationUrl() {
                return fullScreenAnimationUrl;
            }

            public void setFullScreenAnimationUrl(String fullScreenAnimationUrl) {
                this.fullScreenAnimationUrl = fullScreenAnimationUrl;
            }

            public String getFullScreenAnimationZipUrl() {
                return fullScreenAnimationZipUrl;
            }

            public void setFullScreenAnimationZipUrl(String fullScreenAnimationZipUrl) {
                this.fullScreenAnimationZipUrl = fullScreenAnimationZipUrl;
            }
        }

        public static class FansClubDTO {
            private int id;
            private String createdAt;
            private String updatedAt;
            private String authorUid;
            private String name;
            private int originPrice;
            private int price;
            private int productId;
            private int fansCount;
            private boolean onOff;
            private boolean isJoined;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getAuthorUid() {
                return authorUid;
            }

            public void setAuthorUid(String authorUid) {
                this.authorUid = authorUid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOriginPrice() {
                return originPrice;
            }

            public void setOriginPrice(int originPrice) {
                this.originPrice = originPrice;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public int getFansCount() {
                return fansCount;
            }

            public void setFansCount(int fansCount) {
                this.fansCount = fansCount;
            }

            public boolean isOnOff() {
                return onOff;
            }

            public void setOnOff(boolean onOff) {
                this.onOff = onOff;
            }

            public boolean isIsJoined() {
                return isJoined;
            }

            public void setIsJoined(boolean isJoined) {
                this.isJoined = isJoined;
            }
        }

        public static class CurUserDTO {
            private boolean isRoomManager;

            public boolean isIsRoomManager() {
                return isRoomManager;
            }

            public void setIsRoomManager(boolean isRoomManager) {
                this.isRoomManager = isRoomManager;
            }
        }

        public static class UserDTO {
            private int id;
            private String userId;
            private String avatar;
            private String nickname;
            private boolean isFollowed;
            private int vipLevel;
            private int vipType;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }

            public int getVipLevel() {
                return vipLevel;
            }

            public void setVipLevel(int vipLevel) {
                this.vipLevel = vipLevel;
            }

            public int getVipType() {
                return vipType;
            }

            public void setVipType(int vipType) {
                this.vipType = vipType;
            }
        }

        public static class BarrageColorsDTO {
            private String textColor;
            private String bgColor;
            private double bgColorAlpha;

            public String getTextColor() {
                return textColor;
            }

            public void setTextColor(String textColor) {
                this.textColor = textColor;
            }

            public String getBgColor() {
                return bgColor;
            }

            public void setBgColor(String bgColor) {
                this.bgColor = bgColor;
            }

            public double getBgColorAlpha() {
                return bgColorAlpha;
            }

            public void setBgColorAlpha(double bgColorAlpha) {
                this.bgColorAlpha = bgColorAlpha;
            }
        }
    }
}
