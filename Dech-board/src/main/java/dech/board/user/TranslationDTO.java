package dech.board.user;

public class TranslationDTO {
 
    
    private String text;
   
    private String source;
   
    private String target;

    
    public TranslationDTO(String text, String source, String target) {
        this.text = text;
        this.source = source;
        this.target = target;
    }
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }


    public String getSorce() {
        return source;
    }
    public void setSorce(String sorce) {
        this.source = sorce;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
