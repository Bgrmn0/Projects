import requests

def search_artworks(query):
    base_url = "https://api.artic.edu/api/v1/artworks/search"
    params = {
        'q': query,
        'limit': 20,
    }
    response = requests.get(base_url, params=params)
    if response.status_code == 200:
        return response.json()
    else:
        print("Failed to retrieve data")
        return None


def display_results(data):
    if data and 'data' in data and len(data['data']) > 0:
        base_url = "https://www.artic.edu/artworks/"
        for artwork in data['data']:
            title = artwork.get('title', 'No title available')
            artist_title = artwork.get('artist_title', 'Unknown artist')
            artwork_id = artwork.get('id')
            if artwork_id:
                artwork_url = f"{base_url}{artwork_id}"
                print(f"Title: {title}, Artist: {artist_title}, URL: {artwork_url}")
            else:
                print(f"Title: {title}, Artist: {artist_title}")
    else:
        print("No results found")


def main():
    while True:
        query = input("Enter a search term or 'quit' to exit: ").strip()
        if query.lower() == 'quit':
            break
        else:
            data = search_artworks(query)
            display_results(data)

if __name__ == "__main__":
    main()
