package ru.stqa.java_course.github;


import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by leonto on 4/28/2016.
 */
public class GitHubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("7ce71a2e39c5aec17ad67ad618d3f127c1a0d8da");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("olga-leonteva", "java_course")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
