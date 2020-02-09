DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cross-env NODE_ENV=production forever $DIR/../app.js