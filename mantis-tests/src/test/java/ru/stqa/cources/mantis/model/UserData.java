package ru.stqa.cources.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by leonto on 4/26/2016.
 */
    @Entity
    @Table(name = "mantis_user_table")
    public class UserData {

        @Id
        @Column(name = "id")
        private int id;

        @Column(name = "username")
        private String username;

        @Column(name = "email")
        @Type(type = "string")
        private String email;

        @Column(name = "password")
        private String password;

        public String getPassword() {
            return password;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "UserData{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }

        public UserData withId(int id) {
            this.id = id;
            return this;
        }

        public UserData withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserData withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserData withEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UserData userData = (UserData) o;

            if (id != userData.id) return false;
            if (username != null ? !username.equals(userData.username) : userData.username != null) return false;
            return !(email != null ? !email.equals(userData.email) : userData.email != null);

        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (username != null ? username.hashCode() : 0);
            result = 31 * result + (email != null ? email.hashCode() : 0);
            return result;
        }

    }

