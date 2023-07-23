package com.github.besok.foresterintellijplugin.ftree;

import org.jetbrains.annotations.NotNull;

public class Version implements Comparable<Version> {
    private int major;
    private int minor;
    private int extra;

    public Version(int major, int minor, int extra) {
        this.major = major;
        this.minor = minor;
        this.extra = extra;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getExtra() {
        return extra;
    }

    @Override
    public int compareTo(@NotNull Version o) {
        if (this.major > o.major) {
            return 1;
        } else if (this.major < o.major) {
            return -1;
        }

        if (this.minor > o.minor) {
            return 1;
        } else if (this.minor < o.minor) {
            return -1;
        }

        if (this.extra > o.extra) {
            return 1;
        } else if (this.extra < o.extra) {
            return -1;
        }

        return 0;
    }
}
