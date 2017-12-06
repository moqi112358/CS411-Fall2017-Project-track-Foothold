import math
import pandas as pd

house_host = {}
house_doc = {}
host_des = {}

house = pd.read_csv('database_house.csv')
for i in range(len(house)):
	house_id = house.iloc[i,0]
	house_doc[house_id] = ''
	for j in range(1,8):
		try:
			house_doc[house_id] =  house_doc[house_id] + ' ' + house.iloc[i,j]
		except:
			pass
	house_host[house_id] = house.iloc[i,8]

review = pd.read_csv('database_review.csv')
for i in range(len(review)):
	house_id = review.iloc[i,0]
	try:
		house_doc[house_id] =  house_doc[house_id] + ' ' + review.iloc[i,1]
	except:
		pass

host_info = pd.read_csv('database_host-description.csv')
for i in range(len(host_info)):
	host_id = host_info.iloc[i,0]
	try:
		host_des[host_id] = host_info.iloc[i,1]
	except:
		pass

for key in house_doc.keys():
	host_id = house_host[key]
	if host_id in host_des.keys():
		try:
			house_doc[key] = house_doc[key] + ' ' + host_des[host_id]
		except:
			pass

################################################################
import jieba
import jieba.posseg as pseg
import os
import sys
import string
reload(sys)
sys.setdefaultencoding('utf8')

sFilePath = './segfile'
if not os.path.exists(sFilePath) : 
	os.mkdir(sFilePath)

for key in house_doc.keys():
	seg_list = jieba.cut(house_doc[key],cut_all=True)
	result = []
	for seg in seg_list :
		seg = ''.join(seg.split())
		if (seg != '' and seg != "\n" and seg != "\n\n") :
			result.append(seg)
	f = open("./segfile/"+str(key)+"-seg.txt","w+")
	f.write(' '.join(result))
	f.close()

########################################################################
from sklearn import feature_extraction
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.feature_extraction.text import CountVectorizer
import numpy as np

path = './segfile／'
corpus = []

filelist = []
list = open(path+'filelist.txt')
for line in list.readlines():
	line = line.strip()
	filelist.append(line)

for ff in filelist :
    fname = path + ff
    f = open(fname,'r+')
    content = f.read()
    f.close()
    corpus.append(content)

vectorizer = CountVectorizer(stop_words = 'english')    
transformer = TfidfTransformer()
tfidf = transformer.fit_transform(vectorizer.fit_transform(corpus))
word = vectorizer.get_feature_names() 
weight = tfidf.toarray()             


####################################################################################
keyword = {}
for i in range(len(weight)):
	filename = filelist[i].split('-')[0]
	tag = [word[k] for k in np.where(weight[i] > 0.05)[0]]
	keyword[filename] = tag

output = open("house_tag.csv",'w')
for key in keyword.keys():
	line = ','.join(keyword[key])
	output.write('%s,"%s"\n' % (key,line))
	