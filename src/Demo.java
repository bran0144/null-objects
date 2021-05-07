import java.time.LocalDate;

public class Demo {
    public void claimWarranty(Article article, boolean isInGoodCondition, boolean isNonOperational) {
        LocalDate today = LocalDate.now();

        if (isInGoodCondition && isNonOperational &&
                article.getMoneyBackGuarantee() != null &&
                article.getMoneyBackGuarantee().isValidOn(today)) {
            System.out.println("Offer money back");
        }
        if (isNonOperational &&
                article.getExpressWarranty() != null && article.getExpressWarranty().isValidOn(today)) {
            System.out.println("Offer repair");
        }
    }

    public void run() {

    }
}
