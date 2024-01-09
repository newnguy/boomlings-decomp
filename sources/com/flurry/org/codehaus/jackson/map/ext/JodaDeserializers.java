package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.std.StdScalarDeserializer;
import com.flurry.org.codehaus.jackson.map.util.Provider;
import java.util.Arrays;
import java.util.Collection;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePeriod;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/* loaded from: classes.dex */
public class JodaDeserializers implements Provider {

    /* loaded from: classes.dex */
    public class DateMidnightDeserializer extends JodaDeserializer {
        public DateMidnightDeserializer() {
            super(DateMidnight.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public DateMidnight deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (!jsonParser.isExpectedStartArrayToken()) {
                switch (jsonParser.getCurrentToken()) {
                    case VALUE_NUMBER_INT:
                        return new DateMidnight(jsonParser.getLongValue());
                    case VALUE_STRING:
                        DateTime parseLocal = parseLocal(jsonParser);
                        if (parseLocal == null) {
                            return null;
                        }
                        return parseLocal.toDateMidnight();
                    default:
                        throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array, Number or String");
                }
            }
            jsonParser.nextToken();
            int intValue = jsonParser.getIntValue();
            jsonParser.nextToken();
            int intValue2 = jsonParser.getIntValue();
            jsonParser.nextToken();
            int intValue3 = jsonParser.getIntValue();
            if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "after DateMidnight ints");
            }
            return new DateMidnight(intValue, intValue2, intValue3);
        }
    }

    /* loaded from: classes.dex */
    public class DateTimeDeserializer extends JodaDeserializer {
        public DateTimeDeserializer(Class cls) {
            super(cls);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public ReadableInstant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return new DateTime(jsonParser.getLongValue(), DateTimeZone.UTC);
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return null;
                }
                return new DateTime(trim, DateTimeZone.UTC);
            }
            throw deserializationContext.mappingException(getValueClass());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class JodaDeserializer extends StdScalarDeserializer {
        static final DateTimeFormatter _localDateTimeFormat = ISODateTimeFormat.localDateOptionalTimeParser();

        protected JodaDeserializer(Class cls) {
            super(cls);
        }

        protected DateTime parseLocal(JsonParser jsonParser) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            return _localDateTimeFormat.parseDateTime(trim);
        }
    }

    /* loaded from: classes.dex */
    public class LocalDateDeserializer extends JodaDeserializer {
        public LocalDateDeserializer() {
            super(LocalDate.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (!jsonParser.isExpectedStartArrayToken()) {
                switch (jsonParser.getCurrentToken()) {
                    case VALUE_NUMBER_INT:
                        return new LocalDate(jsonParser.getLongValue());
                    case VALUE_STRING:
                        DateTime parseLocal = parseLocal(jsonParser);
                        if (parseLocal == null) {
                            return null;
                        }
                        return parseLocal.toLocalDate();
                    default:
                        throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array, String or Number");
                }
            }
            jsonParser.nextToken();
            int intValue = jsonParser.getIntValue();
            jsonParser.nextToken();
            int intValue2 = jsonParser.getIntValue();
            jsonParser.nextToken();
            int intValue3 = jsonParser.getIntValue();
            if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "after LocalDate ints");
            }
            return new LocalDate(intValue, intValue2, intValue3);
        }
    }

    /* loaded from: classes.dex */
    public class LocalDateTimeDeserializer extends JodaDeserializer {
        public LocalDateTimeDeserializer() {
            super(LocalDateTime.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (!jsonParser.isExpectedStartArrayToken()) {
                switch (jsonParser.getCurrentToken()) {
                    case VALUE_NUMBER_INT:
                        return new LocalDateTime(jsonParser.getLongValue());
                    case VALUE_STRING:
                        DateTime parseLocal = parseLocal(jsonParser);
                        if (parseLocal == null) {
                            return null;
                        }
                        return parseLocal.toLocalDateTime();
                    default:
                        throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array or Number");
                }
            }
            jsonParser.nextToken();
            int intValue = jsonParser.getIntValue();
            jsonParser.nextToken();
            int intValue2 = jsonParser.getIntValue();
            jsonParser.nextToken();
            int intValue3 = jsonParser.getIntValue();
            jsonParser.nextToken();
            int intValue4 = jsonParser.getIntValue();
            jsonParser.nextToken();
            int intValue5 = jsonParser.getIntValue();
            jsonParser.nextToken();
            int intValue6 = jsonParser.getIntValue();
            int i = 0;
            if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                i = jsonParser.getIntValue();
                jsonParser.nextToken();
            }
            if (jsonParser.getCurrentToken() != JsonToken.END_ARRAY) {
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "after LocalDateTime ints");
            }
            return new LocalDateTime(intValue, intValue2, intValue3, intValue4, intValue5, intValue6, i);
        }
    }

    /* loaded from: classes.dex */
    public class PeriodDeserializer extends JodaDeserializer {
        public PeriodDeserializer() {
            super(ReadablePeriod.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public ReadablePeriod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            switch (jsonParser.getCurrentToken()) {
                case VALUE_NUMBER_INT:
                    return new Period(jsonParser.getLongValue());
                case VALUE_STRING:
                    return new Period(jsonParser.getText());
                default:
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Number or String");
            }
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.util.Provider
    public Collection provide() {
        return Arrays.asList(new DateTimeDeserializer(DateTime.class), new DateTimeDeserializer(ReadableDateTime.class), new DateTimeDeserializer(ReadableInstant.class), new LocalDateDeserializer(), new LocalDateTimeDeserializer(), new DateMidnightDeserializer(), new PeriodDeserializer());
    }
}
