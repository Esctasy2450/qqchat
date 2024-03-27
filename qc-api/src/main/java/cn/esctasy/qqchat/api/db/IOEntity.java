package cn.esctasy.qqchat.api.db;

public class IOEntity {
    long _id;
    long createdAt;

    public IOEntity() {
    }

    public final long objectId() {
        return this._id;
    }

    public final long createdAt() {
        return this.createdAt;
    }

    public final String toJson() {
        return "Plugin.toJson(this)";
    }

    public final void printJson() {
        //Plugin.printJson(this);
    }
}