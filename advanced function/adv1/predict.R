load("adv1.Rdata")
library(randomForest)
case = read.csv("test.csv")

data = read.csv("price_predict_test.csv")
data[,4] = as.numeric(as.matrix(data[,4]))
data = na.omit(data)
data = data[,c("city","state","property_type","zipcode","room_type","accommodates","bathrooms","bedrooms",
               "beds")]
data = rbind(data,case)
rf_fit = predict(rf_model, data[dim(data)[1],])
write.table(rf_fit,"result.csv",quote = FALSE,row.names = FALSE,col.names = FALSE)

