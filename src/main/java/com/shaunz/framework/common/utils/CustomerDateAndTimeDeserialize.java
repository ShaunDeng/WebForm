package com.shaunz.framework.common.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.shaunz.framework.core.YgdrasilConst;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerDateAndTimeDeserialize extends JsonDeserializer<Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat(YgdrasilConst.DATE_FORMART);

    @Override
    public Date deserialize(JsonParser paramJsonParser,DeserializationContext paramDeserializationContext)
            throws IOException, JsonProcessingException {
        String str = paramJsonParser.getText().trim();
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return paramDeserializationContext.parseDate(str);
    }
}
