package me.jing.kecheng.utils.http.gupaiBean;

import java.util.List;

public class ApiResponse {

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
        private PagerDTO pager;
        private List<ListDTO> list;
        private FansClubRightDTO fansClubRight;

        public PagerDTO getPager() {
            return pager;
        }

        public void setPager(PagerDTO pager) {
            this.pager = pager;
        }

        public List<ListDTO> getList() {
            return list;
        }

        public void setList(List<ListDTO> list) {
            this.list = list;
        }

        public FansClubRightDTO getFansClubRight() {
            return fansClubRight;
        }

        public void setFansClubRight(FansClubRightDTO fansClubRight) {
            this.fansClubRight = fansClubRight;
        }

        public static class PagerDTO {
            private int page;
            private int limit;
            private int total;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class FansClubRightDTO {
            private boolean userHasJoined;
            private int nextRightLevel;

            public boolean isUserHasJoined() {
                return userHasJoined;
            }

            public void setUserHasJoined(boolean userHasJoined) {
                this.userHasJoined = userHasJoined;
            }

            public int getNextRightLevel() {
                return nextRightLevel;
            }

            public void setNextRightLevel(int nextRightLevel) {
                this.nextRightLevel = nextRightLevel;
            }
        }

        public static class ListDTO {
            private int id;
            private String createdAt;
            private String updatedAt;
            private String userId;
            private String streamId;
            private String cover;
            private String title;
            private String description;
            private String taskId;
            private int startTime;
            private int endTime;
            private String videoUrl;
            private String sourceUrl;
            private String fileSize;
            private String fileId;
            private String fileFormat;
            private int duration;
            private String streamParam;
            private boolean isTestPlay;
            private int height;
            private int width;
            private int originHeight;
            private int originWidth;
            private boolean isPublished;
            private Object bannedAt;
            private Object banReason;
            private String rectangleCover;
            private boolean isShow;
            private boolean isThemeLive;
            private int historyId;
            private boolean isPaidLive;
            private int paidLiveId;
            private int lrcId;
            private int detailId;
            private boolean isAuthorTop;
            private boolean auditStatus;
            private boolean hasAdviceStock;
            private int roomId;
            private UserDTO user;
            private boolean hasPermission;

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

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
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

            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
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

            public String getSourceUrl() {
                return sourceUrl;
            }

            public void setSourceUrl(String sourceUrl) {
                this.sourceUrl = sourceUrl;
            }

            public String getFileSize() {
                return fileSize;
            }

            public void setFileSize(String fileSize) {
                this.fileSize = fileSize;
            }

            public String getFileId() {
                return fileId;
            }

            public void setFileId(String fileId) {
                this.fileId = fileId;
            }

            public String getFileFormat() {
                return fileFormat;
            }

            public void setFileFormat(String fileFormat) {
                this.fileFormat = fileFormat;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getStreamParam() {
                return streamParam;
            }

            public void setStreamParam(String streamParam) {
                this.streamParam = streamParam;
            }

            public boolean isIsTestPlay() {
                return isTestPlay;
            }

            public void setIsTestPlay(boolean isTestPlay) {
                this.isTestPlay = isTestPlay;
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

            public Object getBannedAt() {
                return bannedAt;
            }

            public void setBannedAt(Object bannedAt) {
                this.bannedAt = bannedAt;
            }

            public Object getBanReason() {
                return banReason;
            }

            public void setBanReason(Object banReason) {
                this.banReason = banReason;
            }

            public String getRectangleCover() {
                return rectangleCover;
            }

            public void setRectangleCover(String rectangleCover) {
                this.rectangleCover = rectangleCover;
            }

            public boolean isIsShow() {
                return isShow;
            }

            public void setIsShow(boolean isShow) {
                this.isShow = isShow;
            }

            public boolean isIsThemeLive() {
                return isThemeLive;
            }

            public void setIsThemeLive(boolean isThemeLive) {
                this.isThemeLive = isThemeLive;
            }

            public int getHistoryId() {
                return historyId;
            }

            public void setHistoryId(int historyId) {
                this.historyId = historyId;
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

            public int getLrcId() {
                return lrcId;
            }

            public void setLrcId(int lrcId) {
                this.lrcId = lrcId;
            }

            public int getDetailId() {
                return detailId;
            }

            public void setDetailId(int detailId) {
                this.detailId = detailId;
            }

            public boolean isIsAuthorTop() {
                return isAuthorTop;
            }

            public void setIsAuthorTop(boolean isAuthorTop) {
                this.isAuthorTop = isAuthorTop;
            }

            public boolean isAuditStatus() {
                return auditStatus;
            }

            public void setAuditStatus(boolean auditStatus) {
                this.auditStatus = auditStatus;
            }

            public boolean isHasAdviceStock() {
                return hasAdviceStock;
            }

            public void setHasAdviceStock(boolean hasAdviceStock) {
                this.hasAdviceStock = hasAdviceStock;
            }

            public int getRoomId() {
                return roomId;
            }

            public void setRoomId(int roomId) {
                this.roomId = roomId;
            }

            public UserDTO getUser() {
                return user;
            }

            public void setUser(UserDTO user) {
                this.user = user;
            }

            public boolean isHasPermission() {
                return hasPermission;
            }

            public void setHasPermission(boolean hasPermission) {
                this.hasPermission = hasPermission;
            }

            public static class UserDTO {
                private String userId;
                private String nickname;
                private String avatar;
                private int vipLevel;
                private int vipType;
                private boolean isLiveAuthor;

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
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

                public boolean isIsLiveAuthor() {
                    return isLiveAuthor;
                }

                public void setIsLiveAuthor(boolean isLiveAuthor) {
                    this.isLiveAuthor = isLiveAuthor;
                }
            }
        }
    }
}
