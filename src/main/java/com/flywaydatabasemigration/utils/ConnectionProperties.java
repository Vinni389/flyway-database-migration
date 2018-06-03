package com.flywaydatabasemigration.utils;

import javax.annotation.concurrent.ThreadSafe;
import org.checkerframework.checker.nullness.qual.NonNull;

@ThreadSafe
public class ConnectionProperties {

  @NonNull
  private volatile String url;

  @NonNull
  private volatile String userName;

  @NonNull
  private volatile String password;

  public ConnectionProperties(@NonNull final String url,
                              @NonNull final String userName,
                              @NonNull final String password) {

    this.url = url;
    this.userName = userName;
    this.password = password;
  }

  public ConnectionProperties() {

    this("not set",
         "not set",
         "not set");
  }

  @NonNull
  public String getUrl() {

    return url;
  }

  @NonNull
  public String getUserName() {

    return userName;
  }

  @NonNull
  public String getPassword() {

    return password;
  }

  public void setUrl(final String url) {

    this.url = url;
  }

  public void setUserName(final String userName) {

    this.userName = userName;
  }

  public void setPassword(final String password) {

    this.password = password;
  }

  @Override
  public String toString() {

    return "ConnectionProperties{" +
        "url='" + url + '\'' +
        ", userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
