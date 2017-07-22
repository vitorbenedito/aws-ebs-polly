# aws-ebs-polly
Simple spring-boot api to request polly mp3 file from AWS and return on response the URL stream or the MP3 stream.

## Request example

POST aws-eb-polly.us-east-2.elasticbeanstalk.com/polly/speech


Body:

    {
      "text" : Test Amazon Polly",
      "languageCode" : "ja-JP",
    }

Response:

https://polly.us-east-2.amazonaws.com/v1/speech?Text=Test%20Amazon%20Polly&VoiceId=Mizuki&OutputFormat=mp3&X-Amz-Security-Token=FQoDYXdzEL%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDBvrDuaDEwY1RKw9dSK3AzgWx8aWT7xclmqFznPnHA4Fu0Nfb6ACcKTuDVucnU5xjQ8LP2SEced6%2B7kGNf4Sah4xYhIoECmmeCmX9y9pzf68IqdAHI14gSEJHyfXGWTj%2B6EAiU2WoIrNEy%2BCx75hJiWz9320s0c%2F5%2BWO45GbgMJIgy2r0w%2B96mFfZcO%2FKng3p1O6G9trjc%2B%2B296Bev54%2F%2BnXGtzJoI23%2FdrQMoC%2F7vgRnMuIOhYTtiD6%2BbOWDLaXsWttYCZuF0i5KmaZy5MjNTvyz2iDtY4PZmJJM52GrXlYWQjnnmYbfFiQqxUotfVpaGIA0cYAKNEfluSur6aOr6FolGqmBQpyaCBNTNvVne7eKSn0E20X9iXaayg0ydkeS6OBygun0zJKFTFwLWw4RYtjOh00QvIocJ20Po5fmzt3sy5O37lZP6hFO3q5%2BpMxuMa38SRgSPPahA%2FR2u602fxpbnuMizu0ApH0zMxD6mBFnlkyeHhrlmiq8z5YmdgeL4ycKCdvX8BH6kln2dQ%2BRnrTp84cTJbYhGWch9wfl4BCnkMqg2a0r9HHGkX11Ph2FiyT0sBskCEfATXPnVLqWYiHP9ypT90oqbXNywU%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20170722T141637Z&X-Amz-SignedHeaders=host&X-Amz-Expires=900&X-Amz-Credential=ASIAIB5M7QSWV32I2LQA%2F20170722%2Fus-east-2%2Fpolly%2Faws4_request&X-Amz-Signature=b50cdc365652a50ccafd0c0bf45d265e2a4890e5c9fff204daef5456d4bde399

## Configure the Environment variables on ESB and deploy

SERVER_PORT
