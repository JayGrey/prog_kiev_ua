package homework5.ex3;

import java.util.Iterator;

class JParser implements Iterable<JParser.Element> {
    private String text;

    public JParser(String text) {
        this.text = text;
    }

    public enum Type {
        STRING, DIGIT, ARRAY_BEGIN, ARRAY_END, OBJECT_BEGIN, OBJECT_END,
        PROPERTY_SEPARATOR, MEMBER_SEPARATOR, BOOLEAN, UNKNOWN
    }

    public static class Element {
        public final String value;
        public final Type type;

        public Element(Type type, String value) {
            this.type = type;
            this.value = value;
        }

        public Element(Type type) {
            this.type = type;
            this.value = "";
        }

        @Override
        public String toString() {
            if (type == Type.STRING || type == Type.BOOLEAN
                    || type == Type.DIGIT) {
                return String.format("%s [%s]", type, value);
            } else {
                return String.format("%s", type);
            }
        }
    }

    @Override
    public Iterator<Element> iterator() {
        return new Iterator<Element>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < text.length();
            }

            @Override
            public Element next() {
                while (true) {
                    char ch = text.charAt(index++);

                    if (ch == '\t' || ch == '\n' || ch == '\r' || ch == ' ') {
                        continue;
                    }

                    if (ch == '"') {
                        int lIndex = text.indexOf('"', index);

                        String tmp = text.substring(index, lIndex);
                        index = lIndex + 1;
                        return new Element(Type.STRING, tmp);
                    }

                    if (ch >= '0' && ch <= '9') {
                        int lIndex = index - 1;
                        while (text.charAt(lIndex) >= '0'
                                && text.charAt(lIndex) <= '9') {
                            lIndex++;
                        }
                        String tmp = text.substring(index - 1, lIndex);
                        index = lIndex + 1;
                        return new Element(Type.DIGIT, tmp);
                    }

                    if (ch == 't') {
                        if (!text.substring(index - 1, index + 3)
                                .equals("true")) {
                            throw new RuntimeException("json parsing error");
                        }
                        index += 3;
                        return new Element(Type.BOOLEAN, "true");

                    }

                    if (ch == 'f') {
                        if (!text.substring(index - 1, index + 4)
                                .equals("false")) {
                            throw new RuntimeException("json parsing error");
                        }
                        index += 4;
                        return new Element(Type.BOOLEAN, "false");
                    }

                    if (ch == '{') {
                        return new Element(Type.OBJECT_BEGIN);
                    }

                    if (ch == '}') {
                        return new Element(Type.OBJECT_END);
                    }

                    if (ch == '[') {
                        return new Element(Type.ARRAY_BEGIN);
                    }

                    if (ch == ']') {
                        return new Element(Type.ARRAY_END);
                    }

                    if (ch == ':') {
                        return new Element(Type.PROPERTY_SEPARATOR);
                    }

                    if (ch == ',') {
                        return new Element(Type.MEMBER_SEPARATOR);
                    }

                    return new Element(Type.UNKNOWN);
                }
            }
        };
    }
}
