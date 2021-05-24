package commands.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Random;

public class GuildMemberRemove extends ListenerAdapter {

    String[] messages = {
            "[member] left. Sorry not sorry."
    };


    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {

        Random rand = new Random();
        int number = rand.nextInt(messages.length);

        EmbedBuilder join = new EmbedBuilder();
        join.setColor(Color.RED);
        join.setDescription(messages[number].replace("[member]", event.getUser().getAsMention()));


        event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();
    }
}
