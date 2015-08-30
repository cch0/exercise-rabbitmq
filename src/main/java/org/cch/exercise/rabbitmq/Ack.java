package org.cch.exercise.rabbitmq;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ack implements Serializable {
    LocalDateTime time;

    public Ack(LocalDateTime time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
