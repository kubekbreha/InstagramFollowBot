package bot;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot {

    private Instagram4j instagram;

    public void LogIn(String username, String password) throws IOException {
        instagram = Instagram4j.builder().username(username).password(password).build();
        instagram.setup();
        instagram.login();
        System.out.println("Is user logged in :" + instagram.isLoggedIn());
    }


    public InstagramUser getUser(String search) throws IOException {
        InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(search));
        return userResult.getUser();
    }


    public List<Long> getUserFollowersPK(InstagramUser instagramUser) throws IOException {
        List<Long> usersList = new ArrayList<Long>();
        InstagramGetUserFollowersResult userFollowers = instagram.sendRequest(new InstagramGetUserFollowersRequest(instagramUser.getPk()));
        List<InstagramUserSummary> users = userFollowers.getUsers();
        for (InstagramUserSummary user : users) {
            usersList.add(user.getPk());
            //System.out.println("User " + user.getUsername() + " follows "+ instagramUser.username);
        }
        System.out.println(instagramUser.username + " have "+ instagramUser.getFollower_count() + " followers.");
        return usersList;
    }


    public List<Long> getUserFollowingPK(InstagramUser instagramUser) throws IOException {
        List<Long> usersList = new ArrayList<Long>();
        InstagramGetUserFollowersResult userFollowers = instagram.sendRequest(new InstagramGetUserFollowingRequest(instagramUser.getPk()));
        List<InstagramUserSummary> users = userFollowers.getUsers();
        for (InstagramUserSummary user : users) {
            usersList.add(user.getPk());
            //System.out.println("User " + instagramUser.username + " follows "+ user.getUsername());
        }
        System.out.println(instagramUser.username + " follows "+ instagramUser.getFollowing_count() + " users.");
        return usersList;
    }

    public void followUsers(List<Long> usersToFollow) throws IOException, InterruptedException {
        for(Long user : usersToFollow) {
            instagram.sendRequest(new InstagramFollowRequest(user));
            Thread.sleep(4000);
        }
    }

    public void unfollowUsers(List<Long> usersToFollow) throws IOException, InterruptedException {
        for(Long user : usersToFollow) {
            instagram.sendRequest(new InstagramUnfollowRequest(user));
            Thread.sleep(20000);
        }
    }
}
