package cn.esctasy.qqchat.bot.event.message.chat.third.bard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BardAnswer {
    private String chosenAnswer;
    private List<String> draftAnswers;
}
